package com.brq.cursomc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.services.ClienteService;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ClienteDomain> buscarId(@PathVariable Integer id) throws RecursoNaoEncontrado {
		
		ClienteDomain clienteDomain = clienteService.buscar(id);
		
		return ResponseEntity.ok().body(clienteDomain);
	}
	
}
