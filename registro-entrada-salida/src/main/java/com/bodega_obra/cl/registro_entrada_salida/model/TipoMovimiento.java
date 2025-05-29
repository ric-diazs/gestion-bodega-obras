package com.bodega_obra.cl.registro_entrada_salida.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_movimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 35)
    private String descripcion; // nombre del tipo de movimiento (entrada o salida)

    // Relacion uno-a-muchos con Movimiento: Solo un tipo de movimiento puede
    // estar relacionado con uno o mas movimientos.
    @OneToMany(mappedBy = "tipoMovimiento")
    @JsonManagedReference(value = "ref-tipo-mov-movimiento")
    private List<Movimiento> movimientos;
}
