package com.bodega_obra.cl.auditoria_y_logs.service;

import com.bodega_obra.cl.auditoria_y_logs.model.LogSistema;
import com.bodega_obra.cl.auditoria_y_logs.repository.LogSistemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogSistemaService {

    private final LogSistemaRepository repository;

    public LogSistemaService(LogSistemaRepository repository) {
        this.repository = repository;
    }

    public LogSistema guardar(LogSistema log) {
        return repository.save(log);
    }

    public List<LogSistema> obtenerTodos() {
        return repository.findAll();
    }

    public LogSistema actualizar(Long id, LogSistema logActualizado) {
        return repository.findById(id).map(log -> {
            log.setUsuarioId(logActualizado.getUsuarioId());
            log.setFecha(logActualizado.getFecha());
            log.setAccion(logActualizado.getAccion());
            log.setEntidadAfectada(logActualizado.getEntidadAfectada());
            log.setDetalles(logActualizado.getDetalles());
            return repository.save(log);
        }).orElseThrow(() -> new RuntimeException("Log no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}