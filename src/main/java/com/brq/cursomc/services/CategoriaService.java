package com.brq.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public CategoriaDomain buscar(Integer id) {
		
		Optional<CategoriaDomain> optCategoriaDomain = categoriaRepository.findById(id);
		return optCategoriaDomain.orElse(null);
	}
	
}
