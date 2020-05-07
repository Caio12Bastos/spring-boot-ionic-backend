package com.brq.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.CidadeDomain;
import com.brq.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<CidadeDomain> buscaPorEstado(Integer estadoId) {
		return cidadeRepository.findCidades(estadoId);
	}
}
