package com.brq.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brq.cursomc.domain.CidadeDomain;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeDomain, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT cidade FROM CidadeDomain cidade WHERE cidade.estado.id = :estadoId ORDER BY cidade.nome")
	public List<CidadeDomain> findCidades(@Param("estadoId") Integer estado_id);
}
