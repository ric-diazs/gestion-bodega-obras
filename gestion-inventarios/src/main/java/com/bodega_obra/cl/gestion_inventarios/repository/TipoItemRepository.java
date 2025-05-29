package com.bodega_obra.cl.gestion_inventarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bodega_obra.cl.gestion_inventarios.model.TipoItem;

@Repository
public interface TipoItemRepository extends JpaRepository<TipoItem, Integer>{

}
