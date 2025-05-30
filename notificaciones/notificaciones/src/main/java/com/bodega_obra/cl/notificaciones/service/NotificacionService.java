package com.bodega_obra.cl.notificaciones.service;

import com.bodega_obra.cl.notificaciones.model.Notificacion;
import com.bodega_obra.cl.notificaciones.repository.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {

    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public Notificacion enviarNotificacion(Long usuarioId, String mensaje, String tipo) {
        Notificacion notificacion = Notificacion.builder()
                .usuarioId(usuarioId)
                .mensaje(mensaje)
                .tipo(tipo)
                .leido(false)
                .fechaEnvio(LocalDateTime.now())
                .build();
        return repository.save(notificacion);
    }

    public List<Notificacion> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public Notificacion marcarComoLeida(Long id) {
        Notificacion n = repository.findById(id).orElseThrow();
        n.setLeido(true);
        return repository.save(n);
    }

    public void eliminarNotificacion(Long id) {
        repository.deleteById(id);
    }
}
