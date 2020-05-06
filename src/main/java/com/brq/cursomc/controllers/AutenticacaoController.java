package com.brq.cursomc.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brq.cursomc.security.UserSpringSecurity;
import com.brq.cursomc.security.util.JWTUtil;
import com.brq.cursomc.services.UserService;

@Controller
@RequestMapping(value = "/autenticacao")
public class AutenticacaoController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value = "/renovar_token", method = RequestMethod.POST)
	public ResponseEntity<Void> renovarToken(HttpServletResponse response) {
		
		UserSpringSecurity userSpringSecurity = UserService.autenticado();
		String token = jwtUtil.generateToken(userSpringSecurity.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
}
