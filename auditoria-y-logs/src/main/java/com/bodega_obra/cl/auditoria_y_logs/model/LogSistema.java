package com.bodega_obra.cl.auditoria_y_logs.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private LocalDateTime fecha;
    private String accion;
    private String entidadAfectada;
    private String detalles;
}