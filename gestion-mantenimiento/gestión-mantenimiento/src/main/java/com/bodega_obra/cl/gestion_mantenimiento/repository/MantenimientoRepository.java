package com.bodega_obra.cl.gestion_mantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bodega_obra.cl.gestion_mantenimiento.model.Mantenimiento;

@Repository

public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer> {

}
