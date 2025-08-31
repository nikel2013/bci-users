package com.bci.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bci.users.configuration.ApplicationProperties;
import com.bci.users.controller.UserController;
import com.bci.users.domain.Constants.Field;
import com.bci.users.dto.PhoneDTO;
import com.bci.users.dto.RegisterDTO;
import com.bci.users.dto.RegisterResponseDTO;
import com.bci.users.repository.IUserRepository;
import com.bci.users.security.JwtService;
import com.bci.users.service.UserService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ControllerTests {
	
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
	void testRest() throws Exception{			
		
		ResponseEntity<RegisterResponseDTO> response = new ResponseEntity<RegisterResponseDTO>(HttpStatus.OK);
		
		Mockito.when(restTemplate.exchange(
				ArgumentMatchers.eq("/users/register"),
				ArgumentMatchers.eq(HttpMethod.POST),
				ArgumentMatchers.<HttpEntity<RegisterDTO>>any(),
				ArgumentMatchers.<ParameterizedTypeReference<RegisterResponseDTO>>any()
	        )).thenReturn(response);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testRegister() throws Exception{			
		
		ResponseEntity<RegisterResponseDTO> response = new ResponseEntity<RegisterResponseDTO>(HttpStatus.OK);
		
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
		
		when(applicationProperties.getEmailRegex()).thenReturn("^(.+)@(.+).cl$");
		when(applicationProperties.getPasswordRegex()).thenReturn("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
		when(userController.register(request)).thenReturn(response);
		
		userController.register(request);
		assertEquals(HttpStatus.OK, response.getStatusCode());		
	}			
}