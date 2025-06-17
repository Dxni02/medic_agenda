package com.medicagenda.usuarios_service.dominio.model;

import com.medicagenda.usuarios_service.entity.Usuario.TipoUsuario;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String correo;
    private TipoUsuario tipo;
    private String rol;
    private String especialidad;
}
