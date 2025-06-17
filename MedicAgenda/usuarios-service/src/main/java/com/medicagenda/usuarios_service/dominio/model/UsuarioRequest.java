package com.medicagenda.usuarios_service.dominio.model;

import com.medicagenda.usuarios_service.entity.Usuario.TipoUsuario;
import lombok.Data;

@Data
public class UsuarioRequest {
    private String nombre;
    private String correo;
    private String contrasena;
    private TipoUsuario tipo;
    private Integer especialidadId;
    private Integer rolId;
}