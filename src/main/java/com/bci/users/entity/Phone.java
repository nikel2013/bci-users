package com.bci.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import com.bci.users.domain.Constants.Database;
import com.bci.users.domain.Constants.Field;

@Entity
@Table(name = Database.PPHONE_TABLE)
public class Phone extends Audit{

	@Id
	@Column(name = Field.PHONE_ID)
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	
	@ManyToOne(targetEntity = User.class)
	private User user;
	
	@Column(name = Field.PHONE_NUMBER)
	private String number;
	
	@Column(name = Field.CITY_CODE)
	private String cityCode;
	
	@Column(name = Field.COUNTRY_CODE)
	private String countryCode;

    public void setUser(User user) {
        this.user = user;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}