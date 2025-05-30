package com.bodega_obra.cl.gestion_mantenimiento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matenimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mantenimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false, length = 150)
    private String tipo;

     @Column(nullable = false, length = 500)
    private String observaciones;

     @Column(nullable = false, length = 50)
    private String realizado;

     
}
