package com.brq.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brq.cursomc.domain.CategoriaDomain;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaDomain, Integer>{

	
	
}
