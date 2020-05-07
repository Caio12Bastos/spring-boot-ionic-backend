package com.brq.cursomc.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brq.cursomc.domain.CidadeDomain;
import com.brq.cursomc.domain.EstadoDomain;
import com.brq.cursomc.dto.CidadeDTO;
import com.brq.cursomc.dto.EstadoDTO;
import com.brq.cursomc.services.CidadeService;
import com.brq.cursomc.services.EstadoService;

@Controller
@RequestMapping(value = "/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> buscaTodosEstados() {
		
		List<EstadoDomain> listaEstadosDomain = estadoService.buscaTodosEstados();
		
		List<EstadoDTO> listaEstadosDTO = listaEstadosDomain.stream()
								.map(estado -> new EstadoDTO(estado))
								.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaEstadosDTO);
	}
	
	@RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> buscaTodasCidades(@PathVariable Integer estadoId) {
		
		List<CidadeDomain> listaCidadeDomain = cidadeService.buscaPorEstado(estadoId);
		
		List<CidadeDTO> listaCidadesDTO = listaCidadeDomain.stream()
								.map(cidade -> new CidadeDTO(cidade))
								.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaCidadesDTO);
	}

}
