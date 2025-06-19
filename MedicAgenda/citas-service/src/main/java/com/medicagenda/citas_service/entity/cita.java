package com.medicagenda.citas_service.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa una cita médica en el sistema.
 * <p>
 * Esta clase está mapeada a la tabla <code>cita</code> en la base de datos,
 * y es utilizada por JPA para la persistencia de las citas.
 * </p>
 * 
 * <p>
 * Contiene la información básica necesaria para agendar, actualizar o cancelar
 * una cita médica,
 * incluyendo la fecha, hora, estado, observaciones y la relación con paciente y
 * médico.
 * </p>
 * 
 * @author Ander
 * @since 2025-06-18
 */
@Entity
@Table(name = "cita")
@Data
public class Cita {

    /** Identificador único de la cita (clave primaria). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Fecha programada para la cita. */
    @Column(name = "fecha", nullable = false)
    private java.time.LocalDate fecha;

    /** Hora programada para la cita. */
    @Column(name = "hora", nullable = false)
    private java.time.LocalTime hora;

    /** Estado actual de la cita (PENDIENTE, CONFIRMADA, CANCELADA). */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    /** Observaciones o notas adicionales sobre la cita. */
    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    /** ID del paciente que agenda la cita. */
    @Column(name = "paciente_id", nullable = false)
    private Integer pacienteId;

    /** ID del médico que atenderá la cita. */
    @Column(name = "medico_id", nullable = false)
    private Integer medicoId;

    /**
     * Enumeración que representa los estados válidos de una cita.
     */
    public enum Estado {
        PENDIENTE,
        CONFIRMADA,
        CANCELADA
    }
}
