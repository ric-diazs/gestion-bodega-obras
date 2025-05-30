package com.bodega_obra.cl.validacion_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bodega_obra.cl.validacion_usuario.model.Validacion;

@Repository
public interface ValidacionRepository extends JpaRepository<Validacion, Integer > {

}
