package com.bodega_obra.cl.integracion_proveedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodega_obra.cl.integracion_proveedores.model.Proveedor;
import com.bodega_obra.cl.integracion_proveedores.service.ProveedorService;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> enlistarProveedores() {
        List<Proveedor> proveedores = proveedorService.findAll();

        if(proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> buscarProveedor(@PathVariable Integer id) {
        try {
            Proveedor proveedor = proveedorService.findById(id);
            return ResponseEntity.ok(proveedor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.save(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
        try {
            Proveedor proveedorModificar = proveedorService.findById(id);
            proveedorModificar.setId(id);
            proveedorModificar.setNombre(proveedor.getNombre());
            proveedorModificar.setRut(proveedor.getRut());
            proveedorModificar.setDireccion(proveedor.getDireccion());
            proveedorModificar.setComuna(proveedor.getComuna());
            proveedorModificar.setRegion(proveedor.getRegion());
            proveedorModificar.setCorreo(proveedor.getCorreo());
            proveedorModificar.setTelefono(proveedor.getTelefono());

            proveedorService.save(proveedorModificar);
            return ResponseEntity.ok(proveedor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Integer id) {
        try {
            proveedorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
