package com.bci.users.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.bci.users.domain.Constants.Error;
import com.bci.users.domain.Constants.Field;

import io.swagger.v3.oas.annotations.media.Schema;

@Validated
public class PhoneDTO {
	
	@Schema(description = Field.PHONE_NUMBER, required = true, example = "1234567")
	@JsonProperty(Field.PHONE_NUMBER)
	@NotNull(message = Error.DTO_PHONE_NUMBER)
    @NotEmpty(message = Error.DTO_PHONE_NUMBER)
	@JsonFormat(shape = JsonFormat.Shape.STRING)	
	private String number;
	
	@Schema(description = Field.CITY_CODE, required = true, example = "1")
	@JsonProperty(Field.CITY_CODE)
	@NotNull(message = Error.DTO_CITY_CODE)
    @NotEmpty(message = Error.DTO_CITY_CODE)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private String cityCode;

	@Schema(description = Field.COUNTRY_CODE, required = true, example = "57")
	@JsonProperty(Field.COUNTRY_CODE)
	@NotNull(message = Error.DTO_COUNTRY_CODE)
    @NotEmpty(message = Error.DTO_COUNTRY_CODE)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private String countryCode;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}