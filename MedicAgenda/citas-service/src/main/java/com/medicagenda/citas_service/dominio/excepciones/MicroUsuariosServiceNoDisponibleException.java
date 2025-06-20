package com.medicagenda.citas_service.dominio.excepciones;

/**
 * Excepción lanzada cuando el MicroUsuarios-service no está disponible
 * o no responde a las solicitudes del microservicio de citas.
 * <p>
 * Permite informar al usuario o cliente que la validación de usuarios
 * no pudo realizarse por problemas de comunicación con el microservicio externo.
 * </p>
 *
 * @author Ander
 * @since 2025-06-19
 */
public class MicroUsuariosServiceNoDisponibleException extends RuntimeException {

    /**
     * Crea una nueva excepción con un mensaje por defecto.
     */
    public MicroUsuariosServiceNoDisponibleException() {
        super("El MicroUsuarios-service no está disponible. Intente más tarde.");
    }

    /**
     * Crea una nueva excepción con un mensaje personalizado.
     *
     * @param mensaje mensaje descriptivo del error
     */
    public MicroUsuariosServiceNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
