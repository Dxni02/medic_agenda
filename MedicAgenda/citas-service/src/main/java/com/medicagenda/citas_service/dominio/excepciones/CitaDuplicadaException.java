package com.medicagenda.citas_service.dominio.excepciones;

/**
 * Excepción de negocio que indica que ya existe una cita registrada
 * para el paciente o médico en la misma fecha y hora.
 * <p>
 * Esta excepción se utiliza para evitar la duplicidad de citas en el sistema,
 * garantizando la integridad de la agenda médica.
 * </p>
 *
 * @author Ander
 * @since 2025-06-19
 */
public class CitaDuplicadaException extends RuntimeException {

    /**
     * Crea una nueva excepción indicando el motivo de la duplicidad.
     *
     * @param mensaje mensaje descriptivo del conflicto detectado
     */
    public CitaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
