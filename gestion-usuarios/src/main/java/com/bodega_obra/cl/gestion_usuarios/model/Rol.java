package com.bodega_obra.cl.gestion_usuarios.model;

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
@Table(name = "rol_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 35)
    private String nombreRol;

    @Column(nullable = false, length = 35)
    private String permisos;

    // Relacion uno-a-muchos a Usuario: Un usuario solo puede estar asociado a un solo Rol.
    @OneToMany(mappedBy = "rol")
    @JsonManagedReference
    private List<Usuario> usuarios;
}
