package com.bodega_obra.cl.monitoreo_sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bodega_obra.cl.monitoreo_sistema.model.RegistroMonitoreo;

@Repository
public interface RegistroMonitoreoRepository extends JpaRepository<RegistroMonitoreo, Integer> {

}
