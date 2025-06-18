package com.medicagenda.usuarios_service.dominio.model;

import com.medicagenda.usuarios_service.entity.Usuario.TipoUsuario;
import lombok.Data;

/**
 * Objeto de transferencia que representa la solicitud para crear o actualizar un usuario.
 * <p>
 * Este DTO se utiliza para recibir datos enviados desde el cliente en operaciones
 * de creación o edición de usuarios.
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
@Data
public class UsuarioRequest {

    /**
     * Nombre completo del usuario.
     */
    private String nombre;

    /**
     * Correo electrónico del usuario.
     */
    private String correo;

    /**
     * Contraseña del usuario.
     */
    private String contrasena;

    /**
     * Tipo de usuario (PACIENTE, MEDICO, ADMIN, etc.).
     */
    private TipoUsuario tipo;

    /**
     * ID de la especialidad asociada (solo aplicable para médicos).
     */
    private Integer especialidadId;

    /**
     * ID del rol asignado (solo aplicable para tipos de usuario distintos a PACIENTE o MEDICO).
     */
    private Integer rolId;
}
