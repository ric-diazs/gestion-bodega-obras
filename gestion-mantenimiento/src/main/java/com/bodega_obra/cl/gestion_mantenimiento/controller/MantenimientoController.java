package com.bodega_obra.cl.gestion_mantenimiento.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bodega_obra.cl.gestion_mantenimiento.model.Mantenimiento;
import com.bodega_obra.cl.gestion_mantenimiento.service.MantenimientoService;

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




@RestController
@RequestMapping("/api/v1/mantenimientos")

public class MantenimientoController {
    @Autowired
    private MantenimientoService mantenimientoService;

    @GetMapping
    public ResponseEntity<List<Mantenimiento>>ListarMantenimento () {
        List<Mantenimiento> mantenimiento = mantenimientoService.findALL();
        
        if(mantenimiento.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mantenimiento);
    }

     @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento> buscarIMantenimiento(@PathVariable Integer id) {
        try {
            Mantenimiento mantenimiento= mantenimientoService.findById(id);

            return ResponseEntity.ok(mantenimiento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Mantenimiento> crearManteniemiento(@RequestBody Mantenimiento mantenimiento) {
        Mantenimiento nuevoMantenimiento = mantenimientoService.save(mantenimiento);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMantenimiento);
    }

     @PutMapping("/{id}")
    public ResponseEntity<Mantenimiento> actualizarMantenimiento(@PathVariable Integer id, @RequestBody Mantenimiento mantenimiento) {
        try {
            Mantenimiento mantenimientoAModificar = mantenimientoService.findById(id);

            mantenimientoAModificar.setId(id);
            mantenimientoAModificar.setItem_id(mantenimiento.getItem_id());;
            mantenimientoAModificar.setFecha(mantenimiento.getFecha());
            mantenimientoAModificar.setObservaciones(mantenimiento.getObservaciones());
            mantenimientoAModificar.setRealizado(mantenimiento.getRealizado());

            mantenimientoService.save(mantenimientoAModificar);
            return ResponseEntity.ok(mantenimiento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMamteniemiento(@PathVariable Integer id) {
        try {
            mantenimientoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }




    

}
