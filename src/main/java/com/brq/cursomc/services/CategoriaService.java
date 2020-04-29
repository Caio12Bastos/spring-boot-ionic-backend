package com.brq.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.repositories.CategoriaRepository;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public CategoriaDomain buscar(Integer id) throws RecursoNaoEncontrado {
		
		Optional<CategoriaDomain> optCategoriaDomain = categoriaRepository.findById(id);
		return optCategoriaDomain.orElseThrow(() -> new RecursoNaoEncontrado( 
				"Objeto não encontrado! Id: " + id)); 
	}

	public CategoriaDomain insert(CategoriaDomain categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
	
}
