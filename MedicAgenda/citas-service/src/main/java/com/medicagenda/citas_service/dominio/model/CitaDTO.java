package com.medicagenda.citas_service.dominio.model;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO para exponer datos de citas médicas al cliente.
 * Contiene información de la cita sin exponer detalles sensibles.
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
