package com.brq.cursomc.security.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expriration;
	
	public String generateToken(String userName) {
		
		return Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + expriration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
}
