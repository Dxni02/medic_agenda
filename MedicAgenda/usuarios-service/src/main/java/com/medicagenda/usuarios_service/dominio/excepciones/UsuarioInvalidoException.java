package com.medicagenda.usuarios_service.dominio.excepciones;

/**
 * Excepción lanzada cuando se intenta crear o actualizar un usuario
 * con datos que violan las reglas de negocio definidas.
 * <p>
 * Por ejemplo: asignar una especialidad a un usuario de tipo PACIENTE.
 * </p>
 *
 * @author Ander
 * @since 1.0
 */
public class UsuarioInvalidoException extends RuntimeException {

    /**
     * Construye una nueva excepción con el mensaje de error especificado.
     *
     * @param mensaje el detalle del error que describe la violación de la regla de negocio
     */
    public UsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}
