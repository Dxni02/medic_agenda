package com.medicagenda.citas_service.dominio.model;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO para exponer datos de citas médicas al cliente.
 * <p>
 * Contiene información relevante de la cita sin exponer detalles sensibles.
 * Utilizado como objeto de salida en las respuestas de la API.
 * </p>
 *
 * <ul>
 *     <li>Incluye identificador, fecha, hora, estado, observaciones, paciente y médico.</li>
 *     <li>Documentado con anotaciones Swagger para facilitar la generación de documentación OpenAPI.</li>
 * </ul>
 *
 * @author Ander
 * @since 2025-06-18
 */
@Data
@Schema(description = "Objeto de salida para representar una cita médica")
public class CitaDTO {

    @Schema(description = "Identificador único de la cita", example = "101")
    private Integer id;

    @Schema(description = "Fecha de la cita", example = "2025-06-25")
    private LocalDate fecha;

    @Schema(description = "Hora de la cita", example = "10:30")
    private LocalTime hora;

    @Schema(description = "Estado de la cita (PENDIENTE, CONFIRMADA, CANCELADA)", example = "PENDIENTE")
    private String estado;

    @Schema(description = "Observaciones adicionales para la cita", example = "Paciente con dolor de cabeza persistente")
    private String observaciones;

    @Schema(description = "ID del paciente", example = "202")
    private Integer pacienteId;

    @Schema(description = "ID del médico", example = "105")
    private Integer medicoId;
}
