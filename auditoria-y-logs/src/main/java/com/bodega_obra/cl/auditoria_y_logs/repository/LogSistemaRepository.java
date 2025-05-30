package com.bodega_obra.cl.auditoria_y_logs.repository;

import com.bodega_obra.cl.auditoria_y_logs.model.LogSistema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogSistemaRepository extends JpaRepository<LogSistema, Long> {
}
