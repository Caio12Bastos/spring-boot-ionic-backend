package com.brq.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.services.CategoriaService;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarId(@PathVariable Integer id) throws RecursoNaoEncontrado {
		
		CategoriaDomain categoriaDomain = categoriaService.buscar(id);
		
		return ResponseEntity.ok().body(categoriaDomain);
	}
	
}
