package com.bci.users.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bci.users.dto.RegisterDTO;
import com.bci.users.dto.RegisterResponseDTO;
import com.bci.users.service.UserService;

@RestController
public class UserController implements IUserController{
	
	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public ResponseEntity<RegisterResponseDTO> register(@Valid RegisterDTO request) throws Exception {
		
		RegisterResponseDTO response;
		response = userService.register(request);
				
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}

}