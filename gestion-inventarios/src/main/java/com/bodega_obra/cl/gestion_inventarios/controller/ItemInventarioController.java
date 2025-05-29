package com.bodega_obra.cl.gestion_inventarios.controller;

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

import com.bodega_obra.cl.gestion_inventarios.model.ItemInventario;
import com.bodega_obra.cl.gestion_inventarios.service.ItemInventarioService;

@RestController
@RequestMapping("/api/v1/items-inventario")
public class ItemInventarioController {
    @Autowired
    private ItemInventarioService itemInventarioService;

    @GetMapping
    public ResponseEntity<List<ItemInventario>> listarItemsInventario() {
        List<ItemInventario> itemsInventario = itemInventarioService.findAll();

        if(itemsInventario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(itemsInventario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemInventario> buscarItemInventario(@PathVariable Integer id) {
        try {
            ItemInventario itemInventario = itemInventarioService.findById(id);

            return ResponseEntity.ok(itemInventario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ItemInventario> crearItemInventario(@RequestBody ItemInventario itemInventario) {
        ItemInventario nuevoItem = itemInventarioService.save(itemInventario);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemInventario> actualizarItemInventario(@PathVariable Integer id, @RequestBody ItemInventario itemInventario) {
        try {
            ItemInventario itemAModificar = itemInventarioService.findById(id);

            itemAModificar.setId(id);
            itemAModificar.setNombre(itemInventario.getNombre());
            itemAModificar.setCantidad(itemInventario.getCantidad());
            itemAModificar.setUbicacion(itemInventario.getUbicacion());
            itemAModificar.setTipoItem(itemInventario.getTipoItem());

            itemInventarioService.save(itemAModificar);
            return ResponseEntity.ok(itemInventario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarItemInventario(@PathVariable Integer id) {
        try {
            itemInventarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
