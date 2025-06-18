package com.medicagenda.usuarios_service.dominio.port.in;

import com.medicagenda.usuarios_service.dominio.model.UsuarioDTO;
import com.medicagenda.usuarios_service.dominio.model.UsuarioRequest;

import java.util.List;

/**
 * Puerto de entrada para la lógica de negocio relacionada con usuarios.
 * <p>
 * Define las operaciones que el caso de uso debe implementar y que son invocadas
 * por controladores o adaptadores entrantes (como controladores REST).
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
public interface UsuarioPortIn {

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param request datos necesarios para la creación del usuario
     * @return el usuario creado como {@link UsuarioDTO}
     */
    UsuarioDTO crearUsuario(UsuarioRequest request);

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param id identificador del usuario
     * @return el usuario encontrado como {@link UsuarioDTO}
     */
    UsuarioDTO obtenerUsuarioPorId(Integer id);

    /**
     * Lista todos los usuarios registrados en el sistema.
     *
     * @return lista de usuarios como {@link UsuarioDTO}
     */
    List<UsuarioDTO> listarUsuarios();

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param id identificador del usuario a actualizar
     * @param request datos nuevos del usuario
     * @return el usuario actualizado como {@link UsuarioDTO}
     */
    UsuarioDTO actualizarUsuario(Integer id, UsuarioRequest request);

    /**
     * Elimina un usuario del sistema por su ID.
     *
     * @param id identificador del usuario a eliminar
     */
    void eliminarUsuario(Integer id);
}
