package com.brq.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.domain.PedidoDomain;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoDomain, Integer> {

	@Transactional(readOnly = true)
	Page<PedidoDomain> findByCliente(ClienteDomain clienteDomain, Pageable paginaRequest);

}
