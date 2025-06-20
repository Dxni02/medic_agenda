package com.medicagenda.citas_service.dominio.excepciones;

/**
 * Excepción de negocio lanzada cuando el ID proporcionado
 * no corresponde a un paciente válido en el sistema.
 * <p>
 * Esta excepción se utiliza para garantizar que solo usuarios
 * con rol de paciente puedan ser asignados como tales en una cita.
 * </p>
 *
 * @author Ander
 * @since 2025-06-19
 */
public class PacienteNoValidoException extends RuntimeException {

    /**
     * Crea una nueva excepción indicando el ID inválido.
     *
     * @param id identificador que no corresponde a un paciente válido
     */
    public PacienteNoValidoException(Integer id) {
        super("El ID " + id + " no pertenece a un paciente válido.");
    }
}
