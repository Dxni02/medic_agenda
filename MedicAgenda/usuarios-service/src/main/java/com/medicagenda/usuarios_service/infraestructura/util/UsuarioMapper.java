package com.medicagenda.usuarios_service.infraestructura.util;

import com.medicagenda.usuarios_service.dominio.model.UsuarioDTO;
import com.medicagenda.usuarios_service.dominio.model.UsuarioRequest;
import com.medicagenda.usuarios_service.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "rol.nombre", target = "rol")
    @Mapping(source = "especialidad.nombre", target = "especialidad")
    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(UsuarioRequest request);
}
