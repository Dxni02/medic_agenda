package com.medicagenda.usuarios_service.dominio.model;

import com.medicagenda.usuarios_service.entity.Usuario.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Objeto de transferencia que representa la solicitud para crear o actualizar
 * un usuario.
 * <p>
 * Este DTO se utiliza para recibir datos enviados desde el cliente en
 * operaciones
 * de creación o edición de usuarios.
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
@Data
@Schema(description = "Objeto de entrada para crear o actualizar un usuario")
public class UsuarioRequest {

    /**
     * Nombre completo del usuario.
     */
    @Schema(description = "Nombre completo del usuario", example = "Carlos Méndez")
    private String nombre;

    /**
     * Correo electrónico del usuario.
     */
    @Schema(description = "Correo electrónico único del usuario", example = "carlos.mendez@correo.com")
    private String correo;

    /**
     * Contraseña del usuario.
     */
    @Schema(description = "Contraseña del usuario", example = "secreta123")
    private String contrasena;

    /**
     * Tipo de usuario (PACIENTE, MEDICO, ADMIN, etc.).
     */
    @Schema(description = "Tipo de usuario", example = "MEDICO", required = true)
    private TipoUsuario tipo;

    /**
     * ID de la especialidad asociada (solo aplicable para médicos).
     */
    @Schema(description = "ID de la especialidad (requerido solo si el tipo es MEDICO)", example = "2", nullable = true)
    private Integer especialidadId;

    /**
     * ID del rol asignado (solo aplicable para tipos distintos a PACIENTE o
     * MEDICO).
     */
    @Schema(description = "ID del rol (requerido solo si el tipo no es PACIENTE ni MEDICO)", example = "1", nullable = true)
    private Integer rolId;
}
