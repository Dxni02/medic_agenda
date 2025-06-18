package com.medicagenda.usuarios_service.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa un rol de usuario dentro del sistema.
 * 
 * <p>
 * Ejemplos de roles: ADMIN, MÉDICO, PACIENTE.
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
@Entity
@Data
@Table(name = "rol")
public class Rol {

    /**
     * Identificador único del rol (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre descriptivo del rol (ejemplo: "ADMIN", "MÉDICO", "PACIENTE").
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Constructor que permite crear un rol solo con el ID.
     * Útil para asignaciones por referencia.
     *
     * @param id identificador del rol
     */
    public Rol(Integer id) {
        this.id = id;
    }

    /**
     * Constructor completo con ID y nombre.
     *
     * @param id     identificador del rol
     * @param nombre nombre del rol
     */
    public Rol(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Constructor vacío requerido por JPA.
     */
    public Rol() {
    }
}
