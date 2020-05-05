package com.brq.cursomc.security.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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

	public boolean tokenValido(String token) {
		
		Claims claims = getClaims(token);
		
		if(claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date agora = new Date(System.currentTimeMillis());
			
			if(username != null && expirationDate != null && agora.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {

		try {
			return Jwts.parser().setSigningKey(secret.getBytes())
					.parseClaimsJws(token).getBody();			
		} catch(Exception exception) {
			return null;
		}
	}

	public String getUsername(String token) {
		
		Claims claims = getClaims(token);
		
		if(claims != null) {
			return claims.getSubject();
		}
		
		return null;
	}
}
