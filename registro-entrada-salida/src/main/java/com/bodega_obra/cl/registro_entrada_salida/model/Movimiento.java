package com.bodega_obra.cl.registro_entrada_salida.model;

import java.time.LocalDate;

//import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {
    // Movimiento (id[LISTO], tipo, fecha[LISTO], motivo[LISTO], usuario_id[LISTO], item_id[LISTO])
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Temporal(TemporalType.DATE)
    //@Column(nullable = false)
    //private Date fecha;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = true, length = 55)
    private String motivo;

    // Relacion muchos-a-uno con TipoMovimiento: Muchos movimientos pueden estar relacionados
    //con un tipo de movimiento.
    @ManyToOne
    @JsonBackReference(value = "ref-tipo-mov-movimiento") // Parametro 'value' necesario cuando se usa mas de un @JsonBackReference
    @JoinColumn(name = "id_tipo", nullable = false, foreignKey = @ForeignKey(name = "fk_tipo_movimiento"))
    private TipoMovimiento tipoMovimiento;

    @ManyToOne
    @JsonBackReference(value = "ref-usuario-movimiento")
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "fk_usuario_mov"))
    private Usuario usuario;
    
    @ManyToOne
    @JsonBackReference(value = "ref-item-inventario-movimiento")
    @JoinColumn(name = "id_item", nullable = false, foreignKey = @ForeignKey(name = "fk_item_inventario_mov"))
    private ItemInventario itemInventario;

    //Comentarios.
    // 1. tipo --> id_tipo: foreign key para tabla tipo_movimiento ('entrada', 'salida')
    // 2. Atributos id_usuario e id_item se veran posteriormente.
    //    Se relacionan con microservicios: gestion-usuario y gestion-inventario
}
