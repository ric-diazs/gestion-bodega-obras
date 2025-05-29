package com.bodega_obra.cl.integracion_proveedores.model;

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
@Table(name = "orden_proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 40)
    private String itemPedido; // Material o herramienta pedido/a (ej: cemento,  taladros, etc)

    @Column(nullable = false)
    private Integer cantidad;

    //@Column(nullable = false)
    //@Temporal(TemporalType.DATE)
    //private Date fechaPedido;

    @Column(nullable = false)
    private LocalDate fechaPedido;

    @Column(nullable = false, length = 30)
    private String estadoEntrega;

    // Relacion muchos-a-uno con Proveedor: Un solo proveedor puede tener asociado muchas ordenes de compra.
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_proveedor", nullable = false, foreignKey = @ForeignKey(name = "fk_proveedor"))
    private Proveedor proveedor;
}
