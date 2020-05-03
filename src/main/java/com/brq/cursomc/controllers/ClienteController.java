package com.brq.cursomc.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.dto.ClienteDTO;
import com.brq.cursomc.dto.NovoClienteDTO;
import com.brq.cursomc.services.ClienteService;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ClienteDomain> buscarId(@PathVariable Integer id) throws RecursoNaoEncontrado {

		ClienteDomain clienteDomain = clienteService.buscar(id);

		return ResponseEntity.ok().body(clienteDomain);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirCliente(@Valid @RequestBody NovoClienteDTO novoClienteDTO) {
		ClienteDomain clienteDomain = clienteService.transformDTO(novoClienteDTO);
		clienteDomain = clienteService.inserir(clienteDomain);
		
		URI uriCliente = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(clienteDomain.getId()).toUri();
		
		return ResponseEntity.created(uriCliente).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarCliente(@Valid @RequestBody ClienteDTO clienteDTO,
			@PathVariable Integer id) {
		ClienteDomain clienteDomain = clienteService.transformDTO(clienteDTO);

		clienteDomain.setId(id);
		clienteDomain = clienteService.atualizar(clienteDomain);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletardCliente(@PathVariable Integer id) {

		clienteService.detelar(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> buscarTodasCliente() {

		List<ClienteDomain> listaClienteDomain = clienteService.buscarTodas();
		List<ClienteDTO> listaClienteDTO = listaClienteDomain.stream()
				.map(clienteDomain -> new ClienteDTO(clienteDomain)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaClienteDTO);
	}

	@RequestMapping(value = "/pagina", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> buscarPaginacao(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhaPorPagina", defaultValue = "24") Integer linhaPorPagina,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "order", defaultValue = "ASC") String order) {

		Page<ClienteDomain> listaClienteDomain = clienteService.buscaPaginacao(pagina, linhaPorPagina, orderBy,
				order);

		Page<ClienteDTO> listaClienteDTO = listaClienteDomain
				.map(clienteDomain -> new ClienteDTO(clienteDomain));

		return ResponseEntity.ok().body(listaClienteDTO);
	}

}
