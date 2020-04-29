package com.brq.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brq.cursomc.domain.ClienteDomain;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDomain, Integer>{

}
