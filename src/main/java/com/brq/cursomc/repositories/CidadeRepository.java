package com.brq.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brq.cursomc.domain.CidadeDomain;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeDomain, Integer> {

}
