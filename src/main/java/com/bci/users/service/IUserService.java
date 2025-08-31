package com.bci.users.service;

import com.bci.users.dto.RegisterDTO;
import com.bci.users.dto.RegisterResponseDTO;

public interface IUserService {

    /**
     * register
     * 
     * @param register
     * @return RegisterResponseDTO.class
     */		
	RegisterResponseDTO register(RegisterDTO register) throws Exception;
	
}