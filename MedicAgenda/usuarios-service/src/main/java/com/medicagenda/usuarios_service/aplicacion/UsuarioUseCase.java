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

/**
 * Caso de uso para la gestión de usuarios.
 * Aplica reglas de negocio específicas para creación, actualización y
 * recuperación de usuarios.
 * Forma parte de la capa de aplicación dentro de la arquitectura hexagonal.
 *
 * @author Ander
 * @version 1.0
 * @since 2025-06-18
 */
@Service
@RequiredArgsConstructor
public class UsuarioUseCase implements UsuarioPortIn {

    private final UsuarioPortOut usuarioPortOut;
    private final UsuarioMapper mapper;

    /**
     * Crea un nuevo usuario aplicando reglas de negocio según su tipo:
     * <ul>
     * <li><strong>PACIENTE:</strong> se asigna el rol automáticamente y no puede
     * tener especialidad.</li>
     * <li><strong>MÉDICO:</strong> debe tener una especialidad válida.</li>
     * <li><strong>ADMIN u otros:</strong> puede tener rol y especialidad
     * configurables.</li>
     * </ul>
     *
     * @param request datos del nuevo usuario.
     * @return UsuarioDTO con los datos del usuario creado.
     * @throws UsuarioInvalidoException si se violan reglas de negocio.
     * @throws CorreoDuplicadoException si el correo ya está registrado.
     */
    @Override
    public UsuarioDTO crearUsuario(UsuarioRequest request) {
        Usuario usuario = mapper.toEntity(request);
        Usuario.TipoUsuario tipo = request.getTipo();

        if (Usuario.TipoUsuario.PACIENTE.equals(tipo)) {
            usuario.setRol(new Rol(3, null));
            if (request.getEspecialidadId() != null) {
                throw new UsuarioInvalidoException("Un usuario de tipo PACIENTE no debe tener una especialidad.");
            }
            usuario.setEspecialidad(null);
        } else if (Usuario.TipoUsuario.MEDICO.equals(tipo)) {
            usuario.setRol(new Rol(2, null));
            if (request.getEspecialidadId() == null) {
                throw new UsuarioInvalidoException("Un usuario de tipo MÉDICO debe tener una especialidad.");
            }
            usuario.setEspecialidad(new Especialidad(request.getEspecialidadId(), null));
        } else {
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

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id identificador del usuario.
     * @return UsuarioDTO con los datos del usuario encontrado.
     * @throws RuntimeException si no existe el usuario.
     */
    @Override
    public UsuarioDTO obtenerUsuarioPorId(Integer id) {
        Usuario usuario = usuarioPortOut.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapper.toDTO(usuario);
    }

    /**
     * Lista todos los usuarios registrados.
     *
     * @return Lista de UsuarioDTO.
     */
    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioPortOut.listar()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    /**
     * Actualiza un usuario existente aplicando reglas de negocio.
     * <ul>
     * <li>Valida si el correo es único (excepto para el mismo usuario).</li>
     * <li>Aplica reglas según el tipo (PACIENTE, MÉDICO u otros).</li>
     * </ul>
     *
     * @param id      identificador del usuario a actualizar.
     * @param request nuevos datos del usuario.
     * @return UsuarioDTO actualizado.
     * @throws UsuarioInvalidoException si no se encuentra el usuario o reglas son
     *                                  violadas.
     * @throws CorreoDuplicadoException si el correo ya está en uso por otro
     *                                  usuario.
     */
    @Override
    public UsuarioDTO actualizarUsuario(Integer id, UsuarioRequest request) {
        Usuario existente = usuarioPortOut.buscarPorId(id)
                .orElseThrow(() -> new UsuarioInvalidoException("Usuario no encontrado con ID: " + id));

        usuarioPortOut.listar().stream()
                .filter(u -> u.getCorreo().equals(request.getCorreo()) && !u.getId().equals(id))
                .findAny()
                .ifPresent(u -> {
                    throw new CorreoDuplicadoException("El correo ya está registrado por otro usuario.");
                });

        existente.setNombre(request.getNombre());
        existente.setCorreo(request.getCorreo());
        existente.setContrasena(request.getContrasena());
        existente.setTipo(request.getTipo());

        if (Usuario.TipoUsuario.PACIENTE.equals(request.getTipo())) {
            existente.setRol(new Rol(3, null));
            existente.setEspecialidad(null);
        } else if (Usuario.TipoUsuario.MEDICO.equals(request.getTipo())) {
            existente.setRol(new Rol(2, null));
            if (request.getEspecialidadId() == null) {
                throw new UsuarioInvalidoException("El médico debe tener una especialidad.");
            }
            existente.setEspecialidad(new Especialidad(request.getEspecialidadId(), null));
        } else {
            if (request.getRolId() != null)
                existente.setRol(new Rol(request.getRolId(), null));
            if (request.getEspecialidadId() != null)
                existente.setEspecialidad(new Especialidad(request.getEspecialidadId(), null));
        }

        Usuario guardado = usuarioPortOut.guardar(existente);
        return mapper.toDTO(guardado);
    }

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id identificador del usuario a eliminar.
     */
    @Override
    public void eliminarUsuario(Integer id) {
        usuarioPortOut.eliminar(id);
    }   
}
