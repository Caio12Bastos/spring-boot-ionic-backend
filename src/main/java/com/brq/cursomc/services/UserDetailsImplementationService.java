package com.brq.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.repositories.ClienteRepository;
import com.brq.cursomc.security.UserSpringSecurity;

@Service
public class UserDetailsImplementationService implements UserDetailsService {

	@Autowired ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		ClienteDomain clienteDomain = clienteRepository.findByEmail(email);
		if(clienteDomain == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSpringSecurity(clienteDomain.getId(), clienteDomain.getEmail(), 
				clienteDomain.getSenha(), clienteDomain.getPerfis());
	}

}
