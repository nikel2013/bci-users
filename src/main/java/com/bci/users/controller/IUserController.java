package com.bci.users.controller;

import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bci.users.dto.RegisterDTO;
import com.bci.users.dto.RegisterResponseDTO;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "A través de este módulo podemos administrar los usuarios de la plataforma")
@RequestMapping("/users")
@OpenAPIDefinition(info = @Info(title = "API - Users", 
description = "Contiene los servicios necesarios para administrar los Usuarios de BCI Users",
version = "v1",
contact = @Contact(name = "Elkin Daza",
                   email = "elkin.daza@hotmail.es",
                   url = "https://www.linkedin.com/in/elkin-daza/"
				  )
	)

)
public interface IUserController {

	@PostMapping(
            value = "/register",            
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
    		description = "Registra un usuario en el sistema",
            summary = "Este servicio permite crear un nuevo usuario en BCI Users",
            parameters = { @Parameter(name = "RegisterDTO",
            						  description = "Contiene la información de la operación",
            						  style = ParameterStyle.FORM,
            						  in = ParameterIn.QUERY,
            	                      example = "{\r\n"
            	                      		+ "  \"name\": Elkin,\r\n"
            	                      		+ "  \"email\": \"elkin.daza@bci.cl\",\r\n"
            	                      		+ "  \"password\": \"MiPass123$\",\r\n"
            	                      		+ "  \"phones\": \"[ { \"number\": \"1234567\",\r\n"
            	                      		+ "  \"citycode\": \"1\",\r\n"
            	                      		+ "  \"contrycode\": \"57\" } ]\",\r\n"            	                      		
            	                      		+ "}")
    					}
    		)
			public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid final RegisterDTO request) throws Exception;
}