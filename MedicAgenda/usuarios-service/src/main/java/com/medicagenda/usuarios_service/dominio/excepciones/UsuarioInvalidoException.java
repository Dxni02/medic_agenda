package com.medicagenda.usuarios_service.dominio.excepciones;

/**
 
Se lanza cuando se intenta crear o actualizar un usuario con datos que
violan las reglas de negocio (por ejemplo, tipo PACIENTE con especialidad).*/
public class UsuarioInvalidoException extends RuntimeException {
    public UsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}