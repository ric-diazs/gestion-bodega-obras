package com.bodega_obra.cl.notificaciones.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private String mensaje;
    private String tipo; // EJ: INFO, ADVERTENCIA, ERROR
    private Boolean leido;
    private LocalDateTime fechaEnvio;
}
