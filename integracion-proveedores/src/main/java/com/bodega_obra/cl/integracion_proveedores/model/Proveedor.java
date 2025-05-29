package com.bodega_obra.cl.integracion_proveedores.model;

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
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 55)
    private String nombre;

    @Column(nullable = false, length = 11)
    private String rut;

    @Column(nullable = false, length = 55)
    private String direccion;

    @Column(nullable = false, length = 40)
    private String comuna;

    @Column(nullable = false, length = 40)
    private String region;

    @Column(nullable = false, length = 30)
    private String correo;

    @Column(nullable = false, length = 14)
    private String telefono;

    @OneToMany(mappedBy = "proveedor")
    @JsonManagedReference
    private List<OrdenProveedor> ordenesProveedor;
}
