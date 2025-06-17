package com.medicagenda.usuarios_service.dominio.excepciones;

/**
 
Se lanza cuando se intenta registrar un usuario con un correo ya existente.*/
public class CorreoDuplicadoException extends RuntimeException {
    public CorreoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}