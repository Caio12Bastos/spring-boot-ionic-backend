package com.brq.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.brq.cursomc.security.UserSpringSecurity;

@Service
public class UserService {

	public static UserSpringSecurity autenticado() {
		
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} 
		catch(Exception exception) {
			return null;
		}
	}
}
