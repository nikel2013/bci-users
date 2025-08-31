package com.bci.users.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bci.users.domain.Constants.Field;

@Getter
@Setter
@MappedSuperclass
public class Audit {
	
	@Column(name = Field.AUDIT_ACTIVE, columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean active = true;
	
	@Column(name = Field.AUDIT_CREATED, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private Date created;

	@Column(name = Field.AUDIT_MODIFIED, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@UpdateTimestamp
	private Date modified;

}