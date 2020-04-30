package com.brq.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.repositories.CategoriaRepository;
import com.brq.cursomc.services.exception.DataIntegrityException;
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

	public CategoriaDomain inserir(CategoriaDomain categoria) {
		
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public CategoriaDomain atualizar(CategoriaDomain categoria) {
		
		buscar(categoria.getId());
		return categoriaRepository.save(categoria);
	}

	public void detelar(Integer id) {
		
		buscar(id);
		try {
			categoriaRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException excecao) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<CategoriaDomain> buscarTodas() {
		return categoriaRepository.findAll();
	}
	
	public Page<CategoriaDomain> buscaPaginacao(Integer pagina, Integer linhaPorPagina, String orderBy, String order) {
		
		PageRequest paginaRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(order), orderBy);
		return categoriaRepository.findAll(paginaRequest);
	}
	
}
