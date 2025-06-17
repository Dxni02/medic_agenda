package com.medicagenda.usuarios_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rol")
public class Rol {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Constructor para crear Rol solo con id
    public Rol(Integer id) {
        this.id = id;
    }

    // Constructor para crear Rol con id y nombre
    public Rol(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Rol() {
    }
}