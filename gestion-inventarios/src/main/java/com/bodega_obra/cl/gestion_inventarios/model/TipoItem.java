package com.bodega_obra.cl.gestion_inventarios.model;

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
@Table(name = "tipo_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String descripcion; // sera el nombre del tipo de item (herramienta o material)

    // Relacion uno-a-muchos con ItemInventario: Un tipo de item puede estar vinculado solo a un item.
    @OneToMany(mappedBy = "tipoItem")
    @JsonManagedReference
    private List<ItemInventario> itemsInventario;
}
