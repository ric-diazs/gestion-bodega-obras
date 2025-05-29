package com.bodega_obra.cl.gestion_pedidos.model;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JsonBackReference(value = "ref-pedido-item-pedido")
    @JoinColumn(name = "id_pedido", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido"))
    private Pedido pedido;

    @ManyToOne
    @JsonBackReference(value = "ref-pedido-item-item-inventario")
    @JoinColumn(name = "id_item", nullable = false, foreignKey = @ForeignKey(name = "fk_item_inventario_pedido"))
    private ItemInventario itemInventario;
}
