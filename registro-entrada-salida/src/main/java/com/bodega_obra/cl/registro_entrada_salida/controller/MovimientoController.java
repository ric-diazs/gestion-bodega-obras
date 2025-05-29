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

import com.bodega_obra.cl.registro_entrada_salida.model.Movimiento;
import com.bodega_obra.cl.registro_entrada_salida.service.MovimientoService;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<Movimiento>> enlistarMovimientos() {
        List<Movimiento> movimientos = movimientoService.findAll();

        if(movimientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> buscarMovimiento(@PathVariable Integer id) {
        try {
            Movimiento movimiento = movimientoService.findById(id);
            return ResponseEntity.ok(movimiento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Movimiento> crearMovimiento(@RequestBody Movimiento movimiento) {
        Movimiento nuevoMovimiento = movimientoService.save(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMovimiento);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizarMovimiento(@PathVariable Integer id, @RequestBody Movimiento movimiento) {
        try {
            Movimiento mov = movimientoService.findById(id);
            mov.setId(id);
            mov.setFecha(movimiento.getFecha());
            mov.setMotivo(movimiento.getMotivo());
            mov.setTipoMovimiento(movimiento.getTipoMovimiento());
            mov.setUsuario(movimiento.getUsuario());
            mov.setItemInventario(movimiento.getItemInventario());

            movimientoService.save(mov);
            return ResponseEntity.ok(movimiento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMovimiento(@PathVariable Integer id) {
        try {
            movimientoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
