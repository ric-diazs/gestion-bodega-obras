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

import com.bodega_obra.cl.gestion_inventarios.model.TipoItem;
import com.bodega_obra.cl.gestion_inventarios.service.TipoItemService;

@RestController
@RequestMapping("/api/v1/tipos-item")
public class TipoItemController {
    @Autowired
    private TipoItemService tipoItemService;

    @GetMapping
    public ResponseEntity<List<TipoItem>> enlistarTiposItem() {
        List<TipoItem> tiposItem = tipoItemService.findAll();

        if(tiposItem.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tiposItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoItem> buscarTipoItem(@PathVariable Integer id) {
        try {
            TipoItem tipoItem = tipoItemService.findById(id);

            return ResponseEntity.ok(tipoItem);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoItem> crearTipoItem(@RequestBody TipoItem tipoItem) {
        TipoItem nuevoTipo = tipoItemService.save(tipoItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoItem> actualizarTipoItem(@PathVariable Integer id, @RequestBody TipoItem tipoItem) {
        try {
            TipoItem tipoModificar = tipoItemService.findById(id);
            tipoModificar.setId(id);
            tipoModificar.setDescripcion(tipoItem.getDescripcion());
            
            tipoItemService.save(tipoModificar);
            return ResponseEntity.ok(tipoItem);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTipoItem(@PathVariable Integer id) {
        try {
            tipoItemService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
