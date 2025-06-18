package com.medicagenda.usuarios_service.dominio.model;

import com.medicagenda.usuarios_service.entity.Usuario.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Objeto de transferencia de datos (DTO) para representar la información
 * de un usuario en respuestas del sistema.
 * <p>
 * Este DTO es utilizado para exponer datos al cliente, omitiendo información
 * sensible
 * como la contraseña, y facilitando la comunicación entre capas de la
 * aplicación.
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
@Data
@Schema(description = "DTO de salida que representa un usuario del sistema sin datos sensibles")
public class UsuarioDTO {

    /**
     * Identificador único del usuario.
     */
    @Schema(description = "ID único del usuario", example = "101")
    private Integer id;

    /**
     * Nombre completo del usuario.
     */
    @Schema(description = "Nombre completo del usuario", example = "Laura Sánchez")
    private String nombre;

    /**
     * Correo electrónico del usuario.
     */
    @Schema(description = "Correo electrónico del usuario", example = "laura.sanchez@correo.com")
    private String correo;

    /**
     * Tipo de usuario (PACIENTE, MEDICO, ADMIN, etc.).
     */
    @Schema(description = "Tipo de usuario (PACIENTE, MEDICO, ADMIN, etc.)", example = "MEDICO")
    private TipoUsuario tipo;

    /**
     * Nombre del rol asociado al usuario.
     */
    @Schema(description = "Rol asignado al usuario", example = "MEDICO")
    private String rol;

    /**
     * Nombre de la especialidad del usuario (si aplica).
     */
    @Schema(description = "Especialidad médica asignada (solo si es médico)", example = "Pediatría", nullable = true)
    private String especialidad;
}
