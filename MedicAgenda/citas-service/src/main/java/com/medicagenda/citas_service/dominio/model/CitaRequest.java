package com.medicagenda.citas_service.dominio.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Objeto de entrada para crear o actualizar una cita médica.
 * <p>
 * Contiene los campos necesarios que el cliente debe enviar al sistema.
 * Este DTO es utilizado en el cuerpo de las peticiones HTTP (POST y PUT).
 * </p>
 *
 * <ul>
 *     <li>Incluye fecha, hora, estado, observaciones, pacienteId y medicoId.</li>
 *     <li>Documentado con anotaciones Swagger para facilitar la generación de documentación OpenAPI.</li>
 * </ul>
 *
 * @author Ander
 * @since 2025-06-18
 */
@Data
@Schema(description = "Datos requeridos para registrar o actualizar una cita médica")
public class CitaRequest {

    @Schema(description = "Fecha de la cita en formato YYYY-MM-DD", example = "2025-07-01", required = true)
    private String fecha;

    @Schema(description = "Hora de la cita en formato HH:mm:ss", example = "09:30:00", required = true)
    private String hora;

    @Schema(description = "Estado inicial de la cita", example = "PENDIENTE", required = true, allowableValues = {
            "PENDIENTE", "CONFIRMADA", "CANCELADA" })
    private String estado;

    @Schema(description = "Observaciones adicionales sobre la cita", example = "Paciente con dolor de cabeza", nullable = true)
    private String observaciones;

    @Schema(description = "ID del paciente que solicita la cita", example = "10", required = true)
    private Integer pacienteId;

    @Schema(description = "ID del médico que atenderá la cita", example = "4", required = true)
    private Integer medicoId;
}
