package com.brq.cursomc.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.services.CategoriaService;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoriaDomain> buscarIdCategoria(@PathVariable Integer id) throws RecursoNaoEncontrado {
		
		CategoriaDomain categoriaDomain = categoriaService.buscar(id);
		
		return ResponseEntity.ok().body(categoriaDomain);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirCategoria(@RequestBody CategoriaDomain categoria) {
		categoria = categoriaService.inserir(categoria);
		
		URI uriCategoria = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uriCategoria).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarCategoria(@RequestBody CategoriaDomain categoria, @PathVariable Integer id) {
		
		categoria.setId(id);
		
		categoria = categoriaService.atualizar(categoria);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletardCategoria(@PathVariable Integer id) {
		
		categoriaService.detelar(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
