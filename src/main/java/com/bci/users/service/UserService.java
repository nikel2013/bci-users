package com.bci.users.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.bci.users.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bci.users.domain.Constants.Error;
import com.bci.users.domain.Constants.Field;
import com.bci.users.dto.PhoneDTO;
import com.bci.users.dto.RegisterDTO;
import com.bci.users.dto.RegisterResponseDTO;
import com.bci.users.entity.Phone;
import com.bci.users.entity.User;
import com.bci.users.repository.IUserRepository;
import com.bci.users.security.JwtService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;

    @Autowired
    private ApplicationProperties applicationProperties;
		
	public UserService(JwtService jwtService) {
		this.jwtService = jwtService;
    }

	@Override
	public RegisterResponseDTO register(RegisterDTO register) throws Exception {
		final RegisterResponseDTO response = new RegisterResponseDTO();
		final User u = new User();
		fillUser(u, register);
		validations(u);
					
		u.setToken(getToken(u));
		userRepository.save(u);
		response.setId(u.getId());
		response.setCreated(u.getCreated());
		response.setModified(u.getModified());
		response.setIsactive(u.isActive());
		response.setToken(u.getToken());
		response.setLastLogin(u.getLastLogin());
		
		return response;			
		
	}
	
	private void validations(final User u) throws Exception {
		if(userRepository.existsByEmail(u.getEmail())) {
			throw new Exception(String.format(Error.ALREADY_EXISTS_USER, u.getEmail()));
		} else if(!Pattern.matches(applicationProperties.getEmailRegex(), u.getEmail())) {
			throw new Exception(Error.DTO_FORMAT_USER_EMAIL);
		} else if(!Pattern.matches(applicationProperties.getPasswordRegex(), u.getPassword())) {
			throw new Exception(Error.DTO_INVALID_PASSWORD);
		}
	}	
	
	private void fillUser(final User u, RegisterDTO register) {
		u.setName(register.getName());
		u.setEmail(register.getEmail());
		u.setPassword(register.getPassword());
		u.setPhones(getPhonesToAdd(u, register.getPhones()));
	}
	
	private List<Phone> getPhonesToAdd(final User u, final List<PhoneDTO> lista) {
		List<Phone> phones = new ArrayList<>();
		if(lista != null) {
			for(PhoneDTO item: lista) {
				Phone p = new Phone();
				p.setUser(u);
				p.setNumber(item.getNumber());
				p.setCityCode(item.getCityCode());
				p.setCountryCode(item.getCountryCode());
				
				phones.add(p);
			}
		}
		return phones;		
	}
	
	public String getToken(final User u) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(Field.USER_EMAIL, u.getEmail());		
		return jwtService.create(u.getName(), claims);
	}
}