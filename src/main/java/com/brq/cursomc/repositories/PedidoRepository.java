package com.brq.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brq.cursomc.domain.PedidoDomain;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoDomain, Integer>{

}
