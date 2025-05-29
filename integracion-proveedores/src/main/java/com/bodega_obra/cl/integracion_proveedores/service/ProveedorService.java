package com.bodega_obra.cl.integracion_proveedores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.integracion_proveedores.model.Proveedor;
import com.bodega_obra.cl.integracion_proveedores.repository.ProveedorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Proveedor findById(Integer id) {
        return proveedorRepository.findById(id).get();
    }

    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void delete(Integer id) {
        proveedorRepository.deleteById(id);
    }
}
