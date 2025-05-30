package com.bodega_obra.cl.validacion_usuario.controller;

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

import com.bodega_obra.cl.validacion_usuario.model.Validacion;
import com.bodega_obra.cl.validacion_usuario.service.ValidacionService;

@RestController
@RequestMapping("/api/v1/validaciones")
public class ValidacionController {
    @Autowired
    private ValidacionService validacionService;

    @GetMapping
    public ResponseEntity<List<Validacion>>ListarValidacion () {
        List<Validacion> validacion = validacionService.findALL();
        
        if(validacion.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(validacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Validacion> buscarIValidacion(@PathVariable Integer id) {
        try {
            Validacion validacion= validacionService.findById(id);

            return ResponseEntity.ok(validacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Validacion> crearValidacion(@RequestBody Validacion validacion) {
        Validacion nuevoValidacion = validacionService.save(validacion);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoValidacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Validacion> actualizarValidacon(@PathVariable Integer id,  @RequestBody Validacion validacion) {
        try {
            Validacion validacionAModificar = validacionService.findById(id);

            validacionAModificar.setId(id);
            validacionAModificar.setContraseña(validacion.getContraseña());


            validacionService.save(validacionAModificar);
            return ResponseEntity.ok(validacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarValidacion(@PathVariable Integer id) {
        try {
            validacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
