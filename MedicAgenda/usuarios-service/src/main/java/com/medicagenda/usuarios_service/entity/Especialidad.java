package com.medicagenda.usuarios_service.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa una especialidad médica en el sistema.
 * 
 * <p>
 * Puede estar asociada a usuarios del tipo MÉDICO.
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
@Data
@Entity
@Table(name = "especialidad")
public class Especialidad {

    /**
     * Identificador único de la especialidad (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre de la especialidad médica (ej. "Cardiología", "Pediatría").
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Constructor que permite crear una especialidad solo con el ID.
     * Útil para asignaciones por referencia.
     *
     * @param id identificador de la especialidad
     */
    public Especialidad(Integer id) {
        this.id = id;
    }

    /**
     * Constructor completo con ID y nombre.
     *
     * @param id     identificador de la especialidad
     * @param nombre nombre descriptivo de la especialidad
     */
    public Especialidad(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Constructor vacío requerido por JPA.
     */
    public Especialidad() {
    }
}
