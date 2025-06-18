package com.medicagenda.usuarios_service.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa a un usuario del sistema.
 * 
 * <p>
 * Puede ser de tipo PACIENTE o MÉDICO, y se le asigna un rol y opcionalmente
 * una especialidad.
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    /**
     * Identificador único del usuario (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre completo del usuario.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Correo electrónico del usuario. Debe ser único.
     */
    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    /**
     * Contraseña encriptada del usuario.
     */
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    /**
     * Tipo de usuario dentro del sistema: PACIENTE o MÉDICO.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoUsuario tipo;

    /**
     * Especialidad médica del usuario (solo si es MÉDICO).
     */
    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    /**
     * Rol asignado al usuario (ejemplo: ADMIN, MÉDICO, PACIENTE).
     */
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    /**
     * Enumeración que define los tipos de usuario posibles en el sistema.
     */
    public enum TipoUsuario {
        /** Usuario que recibe atención médica. */
        PACIENTE,

        /** Usuario que brinda atención médica. */
        MEDICO
    }
}
