package com.medicagenda.citas_service.dominio.port.in;

import com.medicagenda.citas_service.dominio.model.CitaDTO;
import com.medicagenda.citas_service.dominio.model.CitaRequest;

import java.util.List;

/**
 * Puerto de entrada para la gestión de citas médicas.
 * <p>
 * Define las operaciones que pueden ser realizadas desde la capa externa
 * hacia la lógica de negocio, siguiendo el principio de inversión de dependencias.
 * Permite desacoplar la lógica de negocio de la infraestructura y facilita la
 * implementación de pruebas y adaptadores.
 * </p>
 *
 * <ul>
 *     <li>Crea, consulta, actualiza y elimina citas médicas.</li>
 *     <li>Expone la API interna para los casos de uso del dominio.</li>
 * </ul>
 *
 * @author Ander
 * @since 2025-06-19
 */
public interface CitaPortIn {

    /**
     * Crea una nueva cita.
     *
     * @param request datos de la cita a crear
     * @return cita creada
     */
    CitaDTO crearCita(CitaRequest request);

    /**
     * Obtiene los datos de una cita específica por ID.
     *
     * @param id identificador de la cita
     * @return cita correspondiente
     */
    CitaDTO obtenerCitaPorId(Integer id);

    /**
     * Lista todas las citas existentes.
     *
     * @return lista de citas
     */
    List<CitaDTO> listarCitas();

    /**
     * Actualiza una cita existente.
     *
     * @param id identificador de la cita
     * @param request datos nuevos
     * @return cita actualizada
     */
    CitaDTO actualizarCita(Integer id, CitaRequest request);

    /**
     * Elimina una cita por su identificador.
     *
     * @param id identificador de la cita
     */
    void eliminarCita(Integer id);
}