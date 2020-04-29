package com.brq.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brq.cursomc.domain.ItemPedidoDomain;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoDomain, Integer>{

}
