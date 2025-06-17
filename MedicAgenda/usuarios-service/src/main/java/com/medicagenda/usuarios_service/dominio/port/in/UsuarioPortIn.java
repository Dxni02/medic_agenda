package com.medicagenda.usuarios_service.dominio.port.in;

import com.medicagenda.usuarios_service.dominio.model.UsuarioDTO;
import com.medicagenda.usuarios_service.dominio.model.UsuarioRequest;

import java.util.List;

public interface UsuarioPortIn {
    UsuarioDTO crearUsuario(UsuarioRequest request);
    UsuarioDTO obtenerUsuarioPorId(Integer id);
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO actualizarUsuario(Integer id, UsuarioRequest request);
    void eliminarUsuario(Integer id);
}
