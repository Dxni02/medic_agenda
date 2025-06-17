package com.medicagenda.usuarios_service.aplicacion;
import com.medicagenda.usuarios_service.dominio.excepciones.CorreoDuplicadoException;
import com.medicagenda.usuarios_service.dominio.excepciones.UsuarioInvalidoException;
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
    /**
     * Crea un nuevo usuario aplicando reglas de negocio según el tipo de usuario.
     * - Si es PACIENTE: asigna automáticamente el rol PACIENTE y no permite
     * especialidad.
     * - Si es MEDICO: asigna automáticamente el rol MEDICO y requiere especialidad.
     * - Otros tipos: permite asignar rol y especialidad según lo recibido.
     *
     * @param request datos del usuario a crear
     * @return UsuarioDTO con la información del usuario creado
     * @throws UsuarioInvalidoException si se violan las reglas de negocio
     */
    public UsuarioDTO crearUsuario(UsuarioRequest request) {
        Usuario usuario = mapper.toEntity(request);
        Usuario.TipoUsuario tipo = request.getTipo();

        if (Usuario.TipoUsuario.PACIENTE.equals(tipo)) {
            // Asignar automáticamente el rol PACIENTE (ID 3)
            usuario.setRol(new Rol(3, null));

            // Validación: PACIENTE no debe tener especialidad
            if (request.getEspecialidadId() != null) {
                throw new UsuarioInvalidoException("Un usuario de tipo PACIENTE no debe tener una especialidad.");
            }
            usuario.setEspecialidad(null);

        } else if (Usuario.TipoUsuario.MEDICO.equals(tipo)) {
            // Asignar automáticamente el rol MEDICO (ID 2)
            usuario.setRol(new Rol(2, null));

            // Validación: MEDICO debe tener especialidad asignada
            if (request.getEspecialidadId() == null) {
                throw new UsuarioInvalidoException("Un usuario de tipo MEDICO debe tener una especialidad asignada.");
            }
            usuario.setEspecialidad(new Especialidad(request.getEspecialidadId(), null));

        } else {
            // Para otros tipos de usuario (ej: ADMIN), asignar rol y especialidad si se
            // especifican
            if (request.getRolId() != null)
                usuario.setRol(new Rol(request.getRolId(), null));
            if (request.getEspecialidadId() != null)
                usuario.setEspecialidad(new Especialidad(request.getEspecialidadId(), null));
        }
        try {
            Usuario guardado = usuarioPortOut.guardar(usuario);
            return mapper.toDTO(guardado);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("uq_correo")) {
                throw new CorreoDuplicadoException("El correo electrónico ya está registrado.");
            }
            throw e; 
        }
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