package com.brq.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.repositories.ClienteRepository;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void enviarNovaSenha(String email) {
	
		ClienteDomain clienteDomain = clienteRepository.findByEmail(email);
		if(clienteDomain == null) {
			throw new RecursoNaoEncontrado("Email não encontrado");
		}
		
		String novaSenha = novaSenha();
		clienteDomain.setSenha(bCryptPasswordEncoder.encode(novaSenha));
		
		clienteRepository.save(clienteDomain);
		emailService.enviaNovaSenhaEmail(clienteDomain, novaSenha);
	}

	private String novaSenha() {
		char[] vet = new char[10];
		
		for(int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		//gera digito
		if(opt == 0) {
			return (char) (random.nextInt(10) + 48);
		}
		//gera letra maiuscula
		else if(opt == 1) {
			return (char) (random.nextInt(26) + 67);
		}
		//gera letra minuscula
		else {
			return (char) (random.nextInt(26) + 97);
		}
	}
	
}
