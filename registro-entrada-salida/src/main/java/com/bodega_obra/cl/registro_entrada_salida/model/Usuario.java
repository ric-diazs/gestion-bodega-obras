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
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @Column(name = "activo", nullable = false, length = 1) // activo = 1 (activo), activo = 0 (inactivo)
    private Character activo;

    // Relacion muchos-a-uno con Rol: Varios usuarios pueden estar asociados a un mismo Rol
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "fk_rol"))
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference(value = "ref-usuario-movimiento")
    private List<Movimiento> movimientos;
}
