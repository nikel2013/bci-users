package com.bci.users.security;

import java.util.Map;

public interface IJwtService {
	
	String create(String subject, Map<String, Object> claims);
	
}
