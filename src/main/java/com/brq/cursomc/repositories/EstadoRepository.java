package com.brq.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brq.cursomc.domain.EstadoDomain;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoDomain, Integer> {

}
