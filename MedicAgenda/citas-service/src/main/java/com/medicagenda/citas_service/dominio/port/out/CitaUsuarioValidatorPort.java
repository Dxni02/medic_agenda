package com.medicagenda.citas_service.dominio.port.out;

import com.medicagenda.citas_service.dominio.model.UsuarioTipo;

/**
 * Puerto de salida para validar existencia y tipo de usuarios
 * consultando el microservicio usuarios-service.
 * <p>
 * Permite abstraer la lógica de consulta de usuarios externos,
 * facilitando la validación de roles y la integración con otros sistemas.
 * </p>
 *
 * @author Ander
 * @since 2025-06-19
 */
public interface CitaUsuarioValidatorPort {

    /**
     * Obtiene el tipo de un usuario por su ID.
     *
     * @param usuarioId ID del usuario a consultar
     * @return tipo de usuario (PACIENTE, MEDICO, ADMIN, DESCONOCIDO)
     */
    UsuarioTipo obtenerTipoUsuario(Integer usuarioId);
}
