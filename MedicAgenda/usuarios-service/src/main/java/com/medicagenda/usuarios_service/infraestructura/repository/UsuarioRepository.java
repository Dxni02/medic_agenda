package com.medicagenda.usuarios_service.infraestructura.repository;

import com.medicagenda.usuarios_service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link Usuario}.
 * 
 * <p>
 * Este repositorio proporciona métodos CRUD automáticos para acceder a la tabla
 * "usuario"
 * en la base de datos. Además, se puede extender con métodos personalizados si
 * es necesario.
 * </p>
 *
 * @author Ander
 * @since 1.0
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Puedes agregar métodos personalizados aquí si lo necesitas
}
