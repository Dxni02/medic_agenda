package com.medicagenda.usuarios_service.infraestructura.adapter;

import com.medicagenda.usuarios_service.dominio.port.out.UsuarioPortOut;
import com.medicagenda.usuarios_service.entity.Usuario;
import com.medicagenda.usuarios_service.infraestructura.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador que implementa el puerto de salida {@link UsuarioPortOut}
 * para interactuar con la base de datos mediante Spring Data JPA.
 * 
 * <p>
 * Esta clase traduce las llamadas del dominio al repositorio JPA
 * correspondiente.
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
@Repository
@RequiredArgsConstructor
public class UsuarioAdapter implements UsuarioPortOut {

    /**
     * Repositorio JPA que permite acceder a la base de datos de usuarios.
     */
    private final UsuarioRepository usuarioRepository;

    /**
     * Guarda o actualiza un usuario en la base de datos.
     *
     * @param usuario entidad usuario a persistir
     * @return usuario guardado con ID generado o actualizado
     */
    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id identificador del usuario
     * @return un {@link Optional} con el usuario si existe, vacío si no
     */
    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Lista todos los usuarios registrados en el sistema.
     *
     * @return lista de usuarios
     */
    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param id identificador del usuario a eliminar
     */
    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
