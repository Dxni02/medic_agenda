package com.medicagenda.usuarios_service.dominio.port.out;

import com.medicagenda.usuarios_service.entity.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de usuarios.
 * <p>
 * Define las operaciones que deben ser implementadas por los adaptadores que interactúan
 * con la fuente de datos (por ejemplo, una base de datos relacional).
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
public interface UsuarioPortOut {

    /**
     * Guarda o actualiza un usuario en la base de datos.
     *
     * @param usuario la entidad {@link Usuario} a guardar
     * @return el usuario guardado con ID asignado
     */
    Usuario guardar(Usuario usuario);

    /**
     * Busca un usuario por su identificador único.
     *
     * @param id el identificador del usuario
     * @return un {@link Optional} que contiene el usuario si existe
     */
    Optional<Usuario> buscarPorId(Integer id);

    /**
     * Lista todos los usuarios existentes en la base de datos.
     *
     * @return lista de usuarios como entidades {@link Usuario}
     */
    List<Usuario> listar();

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id identificador del usuario a eliminar
     */
    void eliminar(Integer id);
}
