package com.medicagenda.citas_service.dominio.excepciones;

/**
 * Excepción de negocio lanzada cuando el ID proporcionado
 * no corresponde a un médico válido en el sistema.
 * <p>
 * Esta excepción se utiliza para garantizar que solo usuarios
 * con rol de médico puedan ser asignados como tales en una cita.
 * </p>
 *
 * @author Ander
 * @since 2025-06-19
 */
public class MedicoNoValidoException extends RuntimeException {

    /**
     * Crea una nueva excepción indicando el ID inválido.
     *
     * @param id identificador que no corresponde a un médico válido
     */
    public MedicoNoValidoException(Integer id) {
        super("El ID " + id + " no pertenece a un médico válido.");
    }
}
