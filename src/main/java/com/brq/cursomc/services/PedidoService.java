package com.brq.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.PedidoDomain;
import com.brq.cursomc.repositories.PedidoRepository;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public PedidoDomain buscar(Integer id) throws RecursoNaoEncontrado {
		
		Optional<PedidoDomain> optPedidoDomain = pedidoRepository.findById(id);
		return optPedidoDomain.orElseThrow(() -> new RecursoNaoEncontrado( 
				"Objeto não encontrado! Id: " + id)); 
	}
	
}
