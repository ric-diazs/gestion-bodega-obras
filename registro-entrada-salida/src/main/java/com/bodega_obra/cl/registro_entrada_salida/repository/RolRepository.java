package com.bodega_obra.cl.registro_entrada_salida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bodega_obra.cl.registro_entrada_salida.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
