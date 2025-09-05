package com.bci.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import com.bci.users.domain.Constants.Info;

import io.swagger.v3.oas.annotations.media.Schema;

public class BaseResponseDTO {

	@Schema(example = "200 OK")
    @JsonIgnore
	private HttpStatus statusCode = HttpStatus.OK;
	
	@Schema(example = Info.SUCCESSFUL)
	private String mensaje = Info.SUCCESSFUL;
	
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}