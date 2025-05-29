package com.bodega_obra.cl.integracion_proveedores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bodega_obra.cl.integracion_proveedores.model.OrdenProveedor;

@Repository
public interface OrdenProveedorRepository extends JpaRepository<OrdenProveedor, Integer> {

}
