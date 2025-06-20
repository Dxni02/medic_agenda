package com.medicagenda.citas_service.infraestructura.excepciones;

import com.medicagenda.citas_service.dominio.excepciones.CitaNoEncontradaException;
import com.medicagenda.citas_service.dominio.excepciones.PacienteNoValidoException;
import com.medicagenda.citas_service.dominio.excepciones.MedicoNoValidoException;
import com.medicagenda.citas_service.dominio.excepciones.CitaDuplicadaException;
import com.medicagenda.citas_service.dominio.excepciones.MicroUsuariosServiceNoDisponibleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global para excepciones no controladas en el microservicio.
 * <p>
 * Captura excepciones comunes y responde con mensajes estructurados,
 * permitiendo que el cliente reciba información clara sobre el error ocurrido.
 * </p>
 *
 * <ul>
 *     <li>Maneja excepciones personalizadas del dominio de citas.</li>
 *     <li>Devuelve códigos HTTP y mensajes adecuados para cada caso.</li>
 *     <li>Incluye marca de tiempo en cada respuesta de error.</li>
 * </ul>
 *
 * @author Ander
 * @since 2025-06-19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja errores de validación del negocio o argumentos inválidos.
     *
     * @param ex excepción lanzada
     * @return respuesta con mensaje y código 400
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());
        error.put("codigo", HttpStatus.BAD_REQUEST.value());
        error.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepción cuando no se encuentra una cita.
     *
     * @param ex excepción lanzada
     * @return respuesta con mensaje y código 404
     */
    @ExceptionHandler(CitaNoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> handleCitaNoEncontrada(CitaNoEncontradaException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());
        error.put("codigo", HttpStatus.NOT_FOUND.value());
        error.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepción cuando el paciente no es válido.
     *
     * @param ex excepción lanzada
     * @return respuesta con mensaje y código 400
     */
    @ExceptionHandler(PacienteNoValidoException.class)
    public ResponseEntity<Map<String, Object>> handlePacienteNoValido(PacienteNoValidoException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());
        error.put("codigo", HttpStatus.BAD_REQUEST.value());
        error.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepción cuando el médico no es válido.
     *
     * @param ex excepción lanzada
     * @return respuesta con mensaje y código 400
     */
    @ExceptionHandler(MedicoNoValidoException.class)
    public ResponseEntity<Map<String, Object>> handleMedicoNoValido(MedicoNoValidoException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());
        error.put("codigo", HttpStatus.BAD_REQUEST.value());
        error.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepción cuando hay una cita duplicada.
     *
     * @param ex excepción lanzada
     * @return respuesta con mensaje y código 409
     */
    @ExceptionHandler(CitaDuplicadaException.class)
    public ResponseEntity<Map<String, Object>> handleCitaDuplicada(CitaDuplicadaException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());
        error.put("codigo", HttpStatus.CONFLICT.value());
        error.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Maneja excepción cuando el MicroUsuarios-service no está disponible.
     *
     * @param ex excepción lanzada
     * @return respuesta con mensaje y código 503
     */
    @ExceptionHandler(MicroUsuariosServiceNoDisponibleException.class)
    public ResponseEntity<Map<String, Object>> handleMicroUsuariosServiceNoDisponible(MicroUsuariosServiceNoDisponibleException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());
        error.put("codigo", HttpStatus.SERVICE_UNAVAILABLE.value());
        error.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * Maneja cualquier otra excepción no específica.
     *
     * @param ex excepción lanzada
     * @return respuesta genérica con código 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", "Error interno del servidor");
        error.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

