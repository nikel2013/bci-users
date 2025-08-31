package com.bci.users.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bci.users.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID>{

	Boolean existsByEmail(String email);
	
}