package com.medicagenda.usuarios_service.infraestructura.excepciones;

import com.medicagenda.usuarios_service.dominio.excepciones.UsuarioInvalidoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para la capa de infraestructura.
 * 
 * <p>
 * Esta clase captura y transforma excepciones comunes en respuestas HTTP
 * amigables,
 * proporcionando mensajes estructurados al cliente.
 * </p>
 *
 * @author Ander
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones relacionadas con violaciones de reglas de negocio en
     * usuarios.
     *
     * @param ex la excepción lanzada por lógica de validación del dominio
     * @return respuesta HTTP 400 con detalles del error
     */
    @ExceptionHandler(UsuarioInvalidoException.class)
    public ResponseEntity<Map<String, Object>> handleUsuarioInvalido(UsuarioInvalidoException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 400);
        error.put("error", "Solicitud inválida");
        error.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Maneja errores por violaciones de integridad de datos en la base de datos,
     * como duplicados de campos únicos (correo, etc.).
     *
     * @param ex excepción de integridad de datos
     * @return respuesta HTTP 409 con mensaje específico si es por correo duplicado
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 409);
        error.put("error", "Conflicto de datos");

        // Intentar detectar si el error es por duplicado de correo
        String rootMsg = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        if (rootMsg != null && rootMsg.toLowerCase().contains("correo")) {
            error.put("message", "Ya existe un usuario con este correo electrónico.");
        } else {
            error.put("message", "Violación de integridad de datos.");
        }

        return ResponseEntity.status(409).body(error);
    }

    /**
     * Manejador genérico para cualquier otra excepción de tipo RuntimeException
     * no capturada explícitamente.
     *
     * @param ex excepción inesperada
     * @return respuesta HTTP 500 con mensaje del error
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(RuntimeException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 500);
        error.put("error", "Error interno");
        error.put("message", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
