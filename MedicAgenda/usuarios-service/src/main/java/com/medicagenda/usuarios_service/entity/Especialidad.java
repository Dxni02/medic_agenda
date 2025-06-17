package com.medicagenda.usuarios_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Constructor para crear Especialidad solo con id
    public Especialidad(Integer id) {
        this.id = id;
    }

    // Constructor para crear Especialidad con id y nombre
    public Especialidad(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Especialidad() {
    }
}