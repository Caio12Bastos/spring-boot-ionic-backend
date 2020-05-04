package com.brq.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.domain.ProdutoDomain;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoDomain, Integer>{

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT produto FROM ProdutoDomain produto INNER JOIN produto.categorias cat "
			+ "WHERE produto.nome LIKE %:nome% AND cat IN :categorias")
	Page<ProdutoDomain> procurar(@Param("nome") String nome, @Param("categorias") List<CategoriaDomain> listaCategoria, 
			Pageable paginaRequest);

}

