package com.medicagenda.usuarios_service.dominio.excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta registrar o actualizar
 * un usuario con un correo electrónico que ya está registrado en el sistema.
 * <p>
 * Esta excepción permite manejar de forma controlada los conflictos de unicidad
 * en el campo "correo" de la entidad {@code Usuario}.
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
public class CorreoDuplicadoException extends RuntimeException {

    /**
     * Crea una nueva instancia de la excepción con un mensaje personalizado.
     *
     * @param mensaje Detalle del error, usualmente indicando que el correo ya existe.
     */
    public CorreoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
