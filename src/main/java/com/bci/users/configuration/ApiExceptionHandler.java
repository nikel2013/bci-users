package com.bci.users.configuration;

import com.bci.users.domain.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bci.users.dto.BaseResponseDTO;

@RestControllerAdvice
public class ApiExceptionHandler{

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BaseResponseDTO errorDTO = new BaseResponseDTO();
        errorDTO.setStatusCode(HttpStatus.BAD_REQUEST);
        errorDTO.setMensaje(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<Object>(errorDTO, errorDTO.getStatusCode());
    }

	@ExceptionHandler({ Exception.class })
    protected ResponseEntity<Object> handleException(Exception ex) {
		BaseResponseDTO errorDTO = new BaseResponseDTO();
		errorDTO.setStatusCode(HttpStatus.BAD_REQUEST);

        if (ex instanceof HttpMessageNotReadableException || ex instanceof HttpMediaTypeNotSupportedException) {
            errorDTO.setMensaje(Constants.Error.INVALID_REQUEST);
        } else {
            errorDTO.setMensaje(ex.getMessage());
        }

        return new ResponseEntity<Object>(errorDTO, errorDTO.getStatusCode());
	}

}