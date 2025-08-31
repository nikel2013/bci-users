package com.bci.users.security;

import java.util.Map;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import com.bci.users.domain.Constants.Security;
import com.bci.users.utils.Dates;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService implements IJwtService{	

	@Override
	public String create(String subject, Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .setIssuedAt(Dates.getCurrentDate())
                .setExpiration(Dates.addToCurrentDate(Security.EXPIRATION_DATE))
                .compact();
	}
}