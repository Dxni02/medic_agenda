package com.medicagenda.usuarios_service.infraestructura.adapter;

import com.medicagenda.usuarios_service.dominio.port.out.UsuarioPortOut;
import com.medicagenda.usuarios_service.entity.Usuario;
import com.medicagenda.usuarios_service.infraestructura.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioAdapter implements UsuarioPortOut {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
