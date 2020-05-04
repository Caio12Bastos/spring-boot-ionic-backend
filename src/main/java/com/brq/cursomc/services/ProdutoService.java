package com.brq.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.domain.ProdutoDomain;
import com.brq.cursomc.repositories.CategoriaRepository;
import com.brq.cursomc.repositories.ProdutoRepository;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public ProdutoDomain buscar(Integer id) throws RecursoNaoEncontrado {
		
		Optional<ProdutoDomain> optProdutoDomain = produtoRepository.findById(id);
		return optProdutoDomain.orElseThrow(() -> new RecursoNaoEncontrado( 
				"Objeto não encontrado! Id: " + id)); 
	}
	
	public Page<ProdutoDomain> procurar(String nome, List<Integer> ids, Integer pagina, 
			Integer linhaPorPagina, String orderBy, String order) {
		
		PageRequest paginaRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(order), orderBy);
		List<CategoriaDomain> listaCategoria = categoriaRepository.findAllById(ids);
		
		return produtoRepository.procurar(nome, listaCategoria, paginaRequest);
	}
}
