package com.brq.cursomc.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.domain.PedidoDomain;
import com.brq.cursomc.dto.CategoriaDTO;
import com.brq.cursomc.services.PedidoService;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PedidoDomain> buscarId(@PathVariable Integer id) throws RecursoNaoEncontrado {
		
		PedidoDomain pedidoDomain = pedidoService.buscar(id);
		
		return ResponseEntity.ok().body(pedidoDomain);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirPedido(@Valid @RequestBody PedidoDomain pedidoDomain) {
		
		pedidoDomain = pedidoService.inserir(pedidoDomain);
		
		URI uriCategoria = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedidoDomain.getId()).toUri();
		
		return ResponseEntity.created(uriCategoria).build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<PedidoDomain>> buscarPaginacao(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina, 
			@RequestParam(value = "linhaPorPagina", defaultValue = "24") Integer linhaPorPagina, 
			@RequestParam(value = "orderBy", defaultValue = "dataPedido") String orderBy, 
			@RequestParam(value = "order", defaultValue = "DESC") String order) {
		
		Page<PedidoDomain> listaPedidoDomain = pedidoService.buscaPaginacao
				(pagina, linhaPorPagina, orderBy, order);
		
		return ResponseEntity.ok().body(listaPedidoDomain);
	}
	
}
