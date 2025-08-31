package com.bci.users.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.bci.users.domain.Constants.Database;
import com.bci.users.domain.Constants.Field;

@Getter
@Setter
@Entity
@Table(name = Database.USER_TABLE, uniqueConstraints={@UniqueConstraint(name = "uk_email", columnNames = {Field.USER_EMAIL})})
public class User extends Audit{

	@Id
	@Column(name = Field.USER_ID)
	@UuidGenerator
	private UUID id;
	
	@Column(name = Field.USER_NAME)
	private String name;
	
	@Column(name = Field.USER_PASSWORD)
	private String password;
	
	@Column(name = Field.USER_EMAIL)
	private String email;
	
	@Column(name = Field.USER_TOKEN)
	private String token;
	
	@Column(name = Field.USER_LAST_LOGIN)
	@CreationTimestamp
	private Date lastLogin;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER, targetEntity = Phone.class)
	private List<Phone> phones;
	
}