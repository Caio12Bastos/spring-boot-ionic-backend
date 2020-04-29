package com.brq.cursomc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brq.cursomc.domain.PedidoDomain;
import com.brq.cursomc.services.PedidoService;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarId(@PathVariable Integer id) throws RecursoNaoEncontrado {
		
		PedidoDomain pedidoDomain = pedidoService.buscar(id);
		
		return ResponseEntity.ok().body(pedidoDomain);
	}
	
}
