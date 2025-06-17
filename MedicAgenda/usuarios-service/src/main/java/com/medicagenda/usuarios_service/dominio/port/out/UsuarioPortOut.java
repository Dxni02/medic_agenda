package com.medicagenda.usuarios_service.dominio.port.out;

import com.medicagenda.usuarios_service.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioPortOut {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Integer id);

    List<Usuario> listar();

    void eliminar(Integer id);
}
