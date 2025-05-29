package com.bodega_obra.cl.monitoreo_sistema.controller;

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

import com.bodega_obra.cl.monitoreo_sistema.model.RegistroMonitoreo;
import com.bodega_obra.cl.monitoreo_sistema.service.RegistroMonitoreoService;

@RestController
@RequestMapping("/api/v1/registros-monitoreo")
public class RegistroMonitoreoController {
    @Autowired
    private RegistroMonitoreoService registroMonitoreoService;

    @GetMapping
    public ResponseEntity<List<RegistroMonitoreo>> enlistarRegistrosMonitoreo() {
        List<RegistroMonitoreo> registrosMonitoreo = registroMonitoreoService.findAll();

        if(registrosMonitoreo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(registrosMonitoreo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroMonitoreo> buscarRegistroMonitoreo(@PathVariable Integer id) {
        try {
            RegistroMonitoreo registroMonitoreo = registroMonitoreoService.findById(id);
            return ResponseEntity.ok(registroMonitoreo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RegistroMonitoreo> crearRegistroMonitoreo(@RequestBody RegistroMonitoreo registroMonitoreo) {
        RegistroMonitoreo nuevoRegistro = registroMonitoreoService.save(registroMonitoreo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRegistro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroMonitoreo> actualizarRegistroMonitoreo(@PathVariable Integer id, @RequestBody RegistroMonitoreo registroMonitoreo) {
        try {
            RegistroMonitoreo registroModificar = registroMonitoreoService.findById(id);
            registroModificar.setId(id);
            registroModificar.setFechaRegistro(registroMonitoreo.getFechaRegistro());
            registroModificar.setHoraRegistro(registroMonitoreo.getHoraRegistro());
            registroModificar.setTipoEvento(registroMonitoreo.getTipoEvento());
            registroModificar.setMensaje(registroMonitoreo.getMensaje());
            registroModificar.setServicio(registroMonitoreo.getServicio());

            registroMonitoreoService.save(registroModificar);
            return ResponseEntity.ok(registroMonitoreo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRegistroMonitoreo(@PathVariable Integer id) {
        try {
            registroMonitoreoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
