package com.medicagenda.citas_service.infraestructura.rest;

import com.medicagenda.citas_service.dominio.model.CitaDTO;
import com.medicagenda.citas_service.dominio.model.CitaRequest;
import com.medicagenda.citas_service.dominio.model.CitaResponse;
import com.medicagenda.citas_service.dominio.port.in.CitaPortIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de citas médicas.
 * <p>
 * Expone los endpoints necesarios para registrar, consultar,
 * actualizar y eliminar citas en el sistema.
 * </p>
 *
 * <ul>
 *     <li>Permite registrar nuevas citas médicas.</li>
 *     <li>Permite consultar, actualizar y eliminar citas existentes.</li>
 *     <li>Incluye documentación Swagger/OpenAPI para facilitar el consumo de la API.</li>
 * </ul>
 *
 * @author Ander
 * @since 2025-06-18
 */
@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
@Tag(name = "Citas", description = "Operaciones sobre citas médicas")
public class CitaController {

    private final CitaPortIn citaService;

    /**
     * Registra una nueva cita médica.
     *
     * @param request datos de la nueva cita
     * @return mensaje de éxito y datos de la cita creada
     */
    @Operation(summary = "Registrar una cita", description = "Registra una nueva cita médica para un paciente con un médico.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cita creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody CitaRequest request) {
        CitaDTO creada = citaService.crearCita(request);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Cita creada exitosamente");
        respuesta.put("timestamp", java.time.LocalDateTime.now());
        respuesta.put("cita", creada);
        return ResponseEntity.status(201).body(respuesta);
    }

    /**
     * Lista todas las citas médicas registradas.
     *
     * @return lista de citas
     */
    @Operation(summary = "Listar todas las citas", description = "Obtiene una lista de todas las citas registradas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Citas listadas correctamente")
    @GetMapping
    public ResponseEntity<List<CitaDTO>> listar() {
        return ResponseEntity.ok(citaService.listarCitas());
    }

    /**
     * Consulta una cita por su ID.
     *
     * @param id identificador de la cita
     * @return cita correspondiente
     */
    @Operation(summary = "Consultar cita por ID", description = "Obtiene la información de una cita médica mediante su identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cita encontrada"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtener(
            @Parameter(description = "ID de la cita a consultar", required = true)
            @PathVariable Integer id) {
        return ResponseEntity.ok(citaService.obtenerCitaPorId(id));
    }

    /**
     * Actualiza una cita existente.
     *
     * @param id identificador de la cita
     * @param request nuevos datos
     * @return mensaje de éxito
     */
    @Operation(summary = "Actualizar cita", description = "Actualiza los datos de una cita existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cita actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CitaResponse> actualizar(
            @Parameter(description = "ID de la cita a actualizar", required = true)
            @PathVariable Integer id,
            @RequestBody CitaRequest request) {
        citaService.actualizarCita(id, request);
        return ResponseEntity.ok(new CitaResponse("Cita actualizada exitosamente"));
    }

    /**
     * Elimina una cita médica.
     *
     * @param id identificador de la cita
     * @return mensaje de éxito
     */
    @Operation(summary = "Eliminar cita", description = "Elimina una cita médica del sistema.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cita eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CitaResponse> eliminar(
            @Parameter(description = "ID de la cita a eliminar", required = true)
            @PathVariable Integer id) {
        citaService.eliminarCita(id);
        return ResponseEntity.ok(new CitaResponse("Cita eliminada exitosamente"));
    }
}
