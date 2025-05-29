package com.bodega_obra.cl.registro_entrada_salida.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, length = 50)
    private String ubicacion;

    // Relacion muchos-a-uno con TipoItem: Muchos items pueden estar vinculado a un solo tipo de item.
    // atributo: tipo --> id_tipo (ej: herramienta, material, etc) --> Foreign Key de tabla tipo_item
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_tipo", nullable = false, foreignKey = @ForeignKey(name = "fk_tipo_item"))
    private TipoItem tipoItem;
    
    @OneToMany(mappedBy = "itemInventario")
    @JsonManagedReference(value = "ref-item-inventario-movimiento")
    private List<Movimiento> movimientos;
}
