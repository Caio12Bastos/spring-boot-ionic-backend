package com.brq.cursomc.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brq.cursomc.controllers.utils.URLUtilsController;
import com.brq.cursomc.domain.ProdutoDomain;
import com.brq.cursomc.dto.ProdutoDTO;
import com.brq.cursomc.services.ProdutoService;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ProdutoDomain> buscarId(@PathVariable Integer id) throws RecursoNaoEncontrado {
		
		ProdutoDomain produtoDomain = produtoService.buscar(id);
		
		return ResponseEntity.ok().body(produtoDomain);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> buscarPaginacao(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhaPorPagina", defaultValue = "24") Integer linhaPorPagina,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "order", defaultValue = "ASC") String order) throws UnsupportedEncodingException {

		String nomeDecode = URLUtilsController.decodeParam(nome);
		List<Integer> ids = URLUtilsController.decodeIntegerList(categorias);
		
		Page<ProdutoDomain> listaProdutoDomain = produtoService.procurar(nomeDecode, ids, 
				pagina, linhaPorPagina, orderBy, order);

		Page<ProdutoDTO> listaProdutoDTO = listaProdutoDomain
				.map(produtoDomain -> new ProdutoDTO(produtoDomain));

		return ResponseEntity.ok().body(listaProdutoDTO);
	}

	
}
