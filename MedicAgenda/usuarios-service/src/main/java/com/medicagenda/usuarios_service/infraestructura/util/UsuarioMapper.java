package com.medicagenda.usuarios_service.infraestructura.util;

import com.medicagenda.usuarios_service.dominio.model.UsuarioDTO;
import com.medicagenda.usuarios_service.dominio.model.UsuarioRequest;
import com.medicagenda.usuarios_service.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper de {@link Usuario} que transforma entre la entidad de base de datos y
 * los modelos {@link UsuarioDTO} y {@link UsuarioRequest}.
 * 
 * <p>
 * Utiliza MapStruct para generar automáticamente las implementaciones en tiempo
 * de compilación.
 * </p>
 *
 * <p>
 * Este componente permite separar la lógica de mapeo del resto del negocio,
 * siguiendo
 * el principio de responsabilidad única (SRP) y apoyando la arquitectura
 * hexagonal.
 * </p>
 *
 * @author Ander
 * @since 1.0
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    /**
     * Convierte una entidad {@link Usuario} a su representación {@link UsuarioDTO}.
     *
     * @param usuario entidad del dominio
     * @return objeto DTO con los datos públicos del usuario
     */
    @Mapping(source = "rol.nombre", target = "rol")
    @Mapping(source = "especialidad.nombre", target = "especialidad")
    UsuarioDTO toDTO(Usuario usuario);

    /**
     * Convierte una solicitud de creación o actualización de usuario a la entidad
     * {@link Usuario}.
     *
     * @param request datos de entrada desde la API
     * @return entidad construida a partir del request
     */
    Usuario toEntity(UsuarioRequest request);
}
