package com.medicagenda.citas_service.dominio.model;

import java.time.LocalDateTime;

/**
 * DTO para respuestas exitosas de operaciones sobre citas.
 * <p>
 * Utilizado para enviar mensajes de confirmación al cliente tras realizar
 * operaciones como creación, actualización o eliminación de citas.
 * Incluye un mensaje descriptivo y la marca de tiempo de la operación.
 * </p>
 *
 * @author Ander
 * @since 2025-06-19
 */
public class CitaResponse {
    /**
     * Mensaje de éxito de la operación realizada.
     */
    private String mensaje;

    /**
     * Fecha y hora en que se generó la respuesta.
     */
    private LocalDateTime timestamp;

    /**
     * Crea una respuesta exitosa con el mensaje proporcionado y la fecha/hora actual.
     *
     * @param mensaje mensaje de éxito a mostrar al cliente
     */
    public CitaResponse(String mensaje) {
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Obtiene el mensaje de la respuesta.
     *
     * @return mensaje de éxito
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Obtiene la marca de tiempo de la respuesta.
     *
     * @return fecha y hora de la respuesta
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
