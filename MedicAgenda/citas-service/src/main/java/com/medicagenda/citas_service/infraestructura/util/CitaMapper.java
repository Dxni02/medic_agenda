package com.medicagenda.citas_service.infraestructura.util;

import com.medicagenda.citas_service.dominio.model.CitaDTO;
import com.medicagenda.citas_service.dominio.model.CitaRequest;
import com.medicagenda.citas_service.entity.Cita;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * Mapeador entre entidades {@link Cita} y modelos de dominio {@link CitaDTO} y
 * {@link CitaRequest}.
 * <p>
 * Utiliza MapStruct para automatizar la conversión entre las capas del
 * microservicio.
 * </p>
 *
 * <ul>
 *     <li>Convierte entidades JPA a DTOs y viceversa.</li>
 *     <li>Permite convertir listas completas de entidades a listas de DTOs.</li>
 * </ul>
 *
 * @author Ander
 * @since 2025-06-18
 */
@Mapper(componentModel = "spring")
public interface CitaMapper {

    /**
     * Convierte una entidad Cita en un DTO.
     *
     * @param cita entidad JPA
     * @return CitaDTO representando los datos de salida
     */
    CitaDTO toDTO(Cita cita);

    /**
     * Convierte un objeto de entrada en una entidad Cita.
     *
     * @param request datos recibidos desde la API
     * @return entidad Cita lista para persistir
     */
    Cita toEntity(CitaRequest request);

    /**
     * Convierte una lista de entidades Cita en una lista de DTOs.
     *
     * @param citas lista de entidades JPA
     * @return lista de CitaDTO
     */
    List<CitaDTO> toDTOList(List<Cita> citas);
}
