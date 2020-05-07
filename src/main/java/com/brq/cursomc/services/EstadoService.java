package com.brq.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.EstadoDomain;
import com.brq.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<EstadoDomain> buscaTodosEstados(){
		return estadoRepository.findAllByOrderByNome();
	}
}
