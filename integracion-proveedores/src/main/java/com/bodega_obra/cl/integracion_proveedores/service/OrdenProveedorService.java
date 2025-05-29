package com.bodega_obra.cl.integracion_proveedores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.integracion_proveedores.model.OrdenProveedor;
import com.bodega_obra.cl.integracion_proveedores.repository.OrdenProveedorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrdenProveedorService {
    @Autowired
    private OrdenProveedorRepository ordenProveedorRepository;

    // Metodos CRUD
    public List<OrdenProveedor> findAll() {
        return ordenProveedorRepository.findAll();
    }

    public OrdenProveedor findById(Integer id) {
        return ordenProveedorRepository.findById(id).get();
    }

    public OrdenProveedor save(OrdenProveedor ordenProveedor) {
        return ordenProveedorRepository.save(ordenProveedor);
    }

    public void delete(Integer id) {
        ordenProveedorRepository.deleteById(id);
    }
}
