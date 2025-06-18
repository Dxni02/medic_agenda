package com.medicagenda.usuarios_service.dominio.model;

import com.medicagenda.usuarios_service.entity.Usuario.TipoUsuario;
import lombok.Data;

/**
 * Objeto de transferencia de datos (DTO) para representar la información
 * de un usuario en respuestas del sistema.
 * <p>
 * Este DTO es utilizado para exponer datos al cliente, omitiendo información sensible
 * como la contraseña, y facilitando la comunicación entre capas de la aplicación.
 * </p>
 *
 * @author Ander
 * @since 1.0
 */
@Data
public class UsuarioDTO {

    /**
     * Identificador único del usuario.
     */
    private Integer id;

    /**
     * Nombre completo del usuario.
     */
    private String nombre;

    /**
     * Correo electrónico del usuario.
     */
    private String correo;

    /**
     * Tipo de usuario (PACIENTE, MEDICO, ADMIN, etc.).
     */
    private TipoUsuario tipo;

    /**
     * Nombre del rol asociado al usuario.
     */
    private String rol;

    /**
     * Nombre de la especialidad del usuario (si aplica).
     */
    private String especialidad;
}
