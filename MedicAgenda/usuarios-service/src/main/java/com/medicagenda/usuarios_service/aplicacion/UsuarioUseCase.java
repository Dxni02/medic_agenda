package com.medicagenda.usuarios_service.aplicacion;

import com.medicagenda.usuarios_service.dominio.model.UsuarioDTO;
import com.medicagenda.usuarios_service.dominio.model.UsuarioRequest;
import com.medicagenda.usuarios_service.dominio.port.in.UsuarioPortIn;
import com.medicagenda.usuarios_service.dominio.port.out.UsuarioPortOut;
import com.medicagenda.usuarios_service.entity.Especialidad;
import com.medicagenda.usuarios_service.entity.Rol;
import com.medicagenda.usuarios_service.entity.Usuario;
import com.medicagenda.usuarios_service.infraestructura.util.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioUseCase implements UsuarioPortIn {

    private final UsuarioPortOut usuarioPortOut;
    private final UsuarioMapper mapper;

    @Override
    public UsuarioDTO crearUsuario(UsuarioRequest request) {
        Usuario usuario = mapper.toEntity(request);

        if (request.getRolId() != null)
            usuario.setRol(new Rol(request.getRolId(), null));
        if (request.getEspecialidadId() != null)
            usuario.setEspecialidad(new Especialidad(request.getEspecialidadId(), null));

        Usuario guardado = usuarioPortOut.guardar(usuario);
        return mapper.toDTO(guardado);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Integer id) {
        Usuario usuario = usuarioPortOut.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapper.toDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioPortOut.listar()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public UsuarioDTO actualizarUsuario(Integer id, UsuarioRequest request) {
        usuarioPortOut.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Usuario actualizado = mapper.toEntity(request);
        actualizado.setId(id);

        if (request.getRolId() != null)
            actualizado.setRol(new Rol(request.getRolId(), null));
        if (request.getEspecialidadId() != null)
            actualizado.setEspecialidad(new Especialidad(request.getEspecialidadId(), null));

        return mapper.toDTO(usuarioPortOut.guardar(actualizado));
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioPortOut.eliminar(id);
    }
}