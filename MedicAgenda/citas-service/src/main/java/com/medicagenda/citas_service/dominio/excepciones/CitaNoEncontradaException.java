package com.medicagenda.citas_service.dominio.excepciones;

/**
 * Excepción de negocio que indica que no se encontró una cita
 * con el identificador proporcionado.
 * <p>
 * Esta excepción se utiliza para notificar que la operación solicitada
 * no puede completarse porque la cita no existe en el sistema.
 * </p>
 *
 * @author Ander
 * @since 2025-06-19
 */
public class CitaNoEncontradaException extends RuntimeException {

    /**
     * Crea una nueva excepción indicando el ID de la cita no encontrada.
     *
     * @param id identificador de la cita que no fue localizada
     */
    public CitaNoEncontradaException(Integer id) {
        super("Cita no encontrada con ID: " + id);
    }
}
