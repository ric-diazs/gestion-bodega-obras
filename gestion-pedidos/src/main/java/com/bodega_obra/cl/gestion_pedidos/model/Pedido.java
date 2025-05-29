package com.bodega_obra.cl.gestion_pedidos.model;

import java.time.LocalDate;
//import java.util.Date;
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
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // La idea es que vaya a buscar el item desde el id_item que esta en la entidad ItemInventario
    // del microservicio gestion-inventario. Lo modificare mas adelante.
    
    /*@Column(nullable = false, length = 35)
    private String nombreItem;*/

    //@Column(nullable = false)
    //private Integer cantidad;

    //@Column(nullable = false)
    //@Temporal(TemporalType.DATE)
    //private Date fechaPedido;

    @Column(nullable = false)
    private LocalDate fechaPedido;
    
    @Column(nullable = false, length = 15)
    private String estado; // Pendiente, aprobado o rechazados

    @Column(nullable = false, length = 15)
    private String prioridad; // alta o baja.

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "fk_usuario_pedido"))
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido")
    @JsonManagedReference(value = "ref-pedido-item-pedido")
    private List<PedidoItem> pedidosItems;

/*
 * Pedido (id, usuario_id, fecha, estado, prioridad) 
   PedidoItem (id, pedido_id, item_id, cantidad)
*/
}
