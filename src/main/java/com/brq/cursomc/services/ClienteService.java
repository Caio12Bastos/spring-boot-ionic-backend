package com.brq.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.repositories.ClienteRepository;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteDomain buscar(Integer id) throws RecursoNaoEncontrado {
		
		Optional<ClienteDomain> optClienteDomain = clienteRepository.findById(id);
		return optClienteDomain.orElseThrow(() -> new RecursoNaoEncontrado( 
				"Objeto não encontrado! Id: " + id)); 
	}
	
}
