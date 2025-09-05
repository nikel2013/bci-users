package com.bci.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import com.bci.users.configuration.ApplicationProperties;
import com.bci.users.controller.UserController;
import com.bci.users.domain.Constants.Error;
import com.bci.users.domain.Constants.Field;
import com.bci.users.domain.Constants.Info;
import com.bci.users.dto.BaseResponseDTO;
import com.bci.users.dto.PhoneDTO;
import com.bci.users.dto.RegisterDTO;
import com.bci.users.dto.RegisterResponseDTO;
import com.bci.users.entity.User;
import com.bci.users.repository.IUserRepository;
import com.bci.users.security.JwtService;
import com.bci.users.service.UserService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ServiceTests {
	
	@Mock
    private RestTemplate restTemplate;	

	@Mock
	private IUserRepository userRepository;
	
	@Mock
	private ApplicationProperties applicationProperties;
	
	@InjectMocks
	private JwtService jwtService;	
	
	@InjectMocks
	private UserService userService = new UserService(mock(JwtService.class));
	
	@InjectMocks
	private UserController userController = new UserController(userService);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

	}
	
	@Test
	void register() throws Exception {
		
		RegisterDTO request = new RegisterDTO();
		request.setName(Field.USER_NAME);		
		request.setPassword("MiPass123$");
		request.setEmail("email@bci.cl");
		
		List<PhoneDTO> phones = new ArrayList<>();
		PhoneDTO p = new PhoneDTO();
		p.setNumber(Field.PHONE_NUMBER);
		p.setCityCode(Field.CITY_CODE);
		p.setCountryCode(Field.COUNTRY_CODE);
		phones.add(p);
		
		request.setPhones(phones);
		
		RegisterResponseDTO response = new RegisterResponseDTO();

		User u = mock(User.class);
		when(applicationProperties.getEmailRegex()).thenReturn("^(.+)@(.+).cl$");
		when(applicationProperties.getPasswordRegex()).thenReturn("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
		when(userRepository.existsByEmail(u.getEmail())).thenReturn(false);
		when(userService.getToken(u)).thenReturn(Field.USER_TOKEN);
				
		response = userService.register(request);
		assertEquals(HttpStatus.OK, response.getStatusCode());		
		assertEquals(Info.SUCCESSFUL, response.getMensaje());		
		assertNull(response.getId());
		assertNull(response.getCreated());
		assertNull(response.getModified());
		assertNull(response.getLastLogin());
		assertNull(response.getToken());
		assertTrue(response.getIsactive());
	}
	
	@Test
	void existsUser() {
		
		RegisterDTO request = mock(RegisterDTO.class);
		User u = mock(User.class);
		
		when(userRepository.existsByEmail(u.getEmail())).thenReturn(true);
		
		Exception eService = assertThrows(
		           Exception.class,
		           () -> userService.register(request));		
		assertEquals(Error.ALREADY_EXISTS_USER, eService.getMessage());
	}
	
	@Test
	void getToken() {
		User u = new User();
		u.setName(Field.USER_NAME);
		u.setPassword(Field.USER_PASSWORD);
		u.setEmail(Field.USER_EMAIL);
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(Field.USER_EMAIL, u.getEmail());				
		
		String token = jwtService.create(u.getName(), claims);
		assertNotNull(token);
	}	
	
	@Test
	void testResponse() {
		BaseResponseDTO response = new BaseResponseDTO();
		response.setStatusCode(HttpStatus.BAD_REQUEST);
		response.setMensaje(Error.DTO_USER_NAME);		
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(Error.DTO_USER_NAME, response.getMensaje());
	}
}