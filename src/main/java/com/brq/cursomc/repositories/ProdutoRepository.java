package com.brq.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brq.cursomc.domain.ProdutoDomain;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoDomain, Integer>{

}
