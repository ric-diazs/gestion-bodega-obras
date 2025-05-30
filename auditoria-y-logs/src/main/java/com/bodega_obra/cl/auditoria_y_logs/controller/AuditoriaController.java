package com.bodega_obra.cl.auditoria_y_logs.controller;

import com.bodega_obra.cl.auditoria_y_logs.model.LogSistema;
import com.bodega_obra.cl.auditoria_y_logs.service.LogSistemaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class AuditoriaController {

    private final LogSistemaService service;

    public AuditoriaController(LogSistemaService service) {
        this.service = service;
    }

    @PostMapping
    public LogSistema guardarLog(@RequestBody LogSistema log) {
        return service.guardar(log);
    }

    @GetMapping
    public List<LogSistema> obtenerLogs() {
        return service.obtenerTodos();
    }

    @PutMapping("/{id}")
    public LogSistema actualizarLog(@PathVariable Long id, @RequestBody LogSistema logActualizado) {
        return service.actualizar(id, logActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarLog(@PathVariable Long id) {
        service.eliminar(id);
    }
}
