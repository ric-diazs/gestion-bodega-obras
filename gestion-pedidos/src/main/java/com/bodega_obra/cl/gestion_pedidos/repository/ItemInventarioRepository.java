package com.bodega_obra.cl.gestion_pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bodega_obra.cl.gestion_pedidos.model.ItemInventario;

@Repository
public interface ItemInventarioRepository extends JpaRepository<ItemInventario, Integer> {

}
