package com.medicagenda.usuarios_service.infraestructura.repository;

import com.medicagenda.usuarios_service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Puedes agregar métodos personalizados aquí si lo necesitas
}
