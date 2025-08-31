package com.bci.users.configuration;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bci.users.dto.BaseResponseDTO;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {        
        BaseResponseDTO errorDTO = new BaseResponseDTO();
        errorDTO.setStatusCode(HttpStatus.BAD_REQUEST);
		errorDTO.setMensaje(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<Object>(errorDTO, errorDTO.getStatusCode());
    }

	@ExceptionHandler({ Exception.class, JdbcSQLIntegrityConstraintViolationException.class })
    protected ResponseEntity<Object> handleException(Exception ex) {    	 
		BaseResponseDTO errorDTO = new BaseResponseDTO();
		errorDTO.setStatusCode(HttpStatus.BAD_REQUEST);
		errorDTO.setMensaje(ex.getMessage());		
        return new ResponseEntity<Object>(errorDTO, errorDTO.getStatusCode());        
	}		
	
}