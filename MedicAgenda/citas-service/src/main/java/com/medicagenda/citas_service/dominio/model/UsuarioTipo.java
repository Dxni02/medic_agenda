package com.medicagenda.citas_service.dominio.model;

/**
 * Enumeración que representa los posibles tipos de usuario en el sistema.
 * <p>
 * Utilizado para distinguir entre pacientes, médicos, administradores
 * y usuarios cuyo tipo no pudo ser determinado.
 * </p>
 *
 * @author Ander
 * @since 2025-06-19
 */
public enum UsuarioTipo {
    /**
     * Usuario con rol de paciente.
     */
    PACIENTE,
    /**
     * Usuario con rol de médico.
     */
    MEDICO,
    /**
     * Usuario con rol de administrador.
     */
    ADMIN,
    /**
     * Usuario cuyo tipo es desconocido o no válido.
     */
    DESCONOCIDO
}
