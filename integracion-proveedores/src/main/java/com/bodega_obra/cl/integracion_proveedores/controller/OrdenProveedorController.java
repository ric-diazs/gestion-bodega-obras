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

import com.bodega_obra.cl.integracion_proveedores.model.OrdenProveedor;
import com.bodega_obra.cl.integracion_proveedores.service.OrdenProveedorService;

@RestController
@RequestMapping("/api/v1/ordenes-proveedor")
public class OrdenProveedorController {
    @Autowired
    private OrdenProveedorService ordenProveedorService;

    @GetMapping
    public ResponseEntity<List<OrdenProveedor>> enlistarOrdenesProveedor() {
        List<OrdenProveedor> ordenesProveedor = ordenProveedorService.findAll();

        if(ordenesProveedor.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ordenesProveedor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenProveedor> buscarOrdenProveedor(@PathVariable Integer id) {
        try {
            OrdenProveedor ordenProveedor = ordenProveedorService.findById(id);
            return ResponseEntity.ok(ordenProveedor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrdenProveedor> crearOrdenProveedor(@RequestBody OrdenProveedor ordenProveedor) {
        OrdenProveedor nuevaOrdenProveedor = ordenProveedorService.save(ordenProveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOrdenProveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenProveedor> actualizarOrdenProveedor(@PathVariable Integer id, @RequestBody OrdenProveedor ordenProveedor) {
        try {
            OrdenProveedor ordenProveedorModificar = ordenProveedorService.findById(id);
            ordenProveedorModificar.setId(id);
            ordenProveedorModificar.setItemPedido(ordenProveedor.getItemPedido());
            ordenProveedorModificar.setCantidad(ordenProveedor.getCantidad());
            ordenProveedorModificar.setFechaPedido(ordenProveedor.getFechaPedido());
            ordenProveedorModificar.setEstadoEntrega(ordenProveedor.getEstadoEntrega());
            ordenProveedorModificar.setProveedor(ordenProveedor.getProveedor());

            ordenProveedorService.save(ordenProveedorModificar);
            return ResponseEntity.ok(ordenProveedor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOrdenProveedor(@PathVariable Integer id) {
        try {
            ordenProveedorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
