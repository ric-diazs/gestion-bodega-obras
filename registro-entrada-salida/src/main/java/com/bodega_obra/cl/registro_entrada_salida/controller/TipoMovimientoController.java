package com.bodega_obra.cl.registro_entrada_salida.controller;

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

import com.bodega_obra.cl.registro_entrada_salida.model.TipoMovimiento;
import com.bodega_obra.cl.registro_entrada_salida.service.TipoMovimientoService;

@RestController
@RequestMapping("/api/v1/tipos-movimiento")
public class TipoMovimientoController {
    @Autowired
    private TipoMovimientoService tipoMovimientoService;

    @GetMapping
    public ResponseEntity<List<TipoMovimiento>> enlistarTiposMovimiento() {
        List<TipoMovimiento> tiposMovimiento = tipoMovimientoService.findAll();

        if(tiposMovimiento.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tiposMovimiento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMovimiento> buscarTipoMovimiento(@PathVariable Integer id) {
        try {
            TipoMovimiento tipoMovimiento = tipoMovimientoService.findById(id);
            return ResponseEntity.ok(tipoMovimiento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoMovimiento> crearTipoMovimiento(@RequestBody TipoMovimiento tipoMovimiento) {
        TipoMovimiento nuevoTipoMovimiento = tipoMovimientoService.save(tipoMovimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipoMovimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoMovimiento> actualizarTipoMovimiento(@PathVariable Integer id, @RequestBody TipoMovimiento tipoMovimiento) {
        try {
            TipoMovimiento tipoModificar = tipoMovimientoService.findById(id);
            tipoModificar.setId(id);
            tipoModificar.setDescripcion(tipoMovimiento.getDescripcion());
            
            tipoMovimientoService.save(tipoModificar);
            return ResponseEntity.ok(tipoMovimiento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTipoMovimiento(@PathVariable Integer id) {
        try {
            tipoMovimientoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
