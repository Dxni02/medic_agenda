package com.medicagenda.citas_service.dominio.port.out;

import com.medicagenda.citas_service.entity.Cita;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para operaciones relacionadas con el almacenamiento de citas
 * médicas.
 * Define las operaciones que deben ser implementadas por adaptadores de
 * infraestructura,
 * como repositorios o servicios externos.
 */
public interface CitaPortOut {

    /**
     * Persiste una cita en el sistema.
     *
     * @param cita la entidad de cita a guardar
     * @return la cita guardada
     */
    Cita guardar(Cita cita);

    /**
     * Busca una cita por su identificador único.
     *
     * @param id el ID de la cita
     * @return un Optional con la cita encontrada o vacío si no existe
     */
    Optional<Cita> buscarPorId(Integer id);

    /**
     * Lista todas las citas disponibles en el sistema.
     *
     * @return lista de citas
     */
    List<Cita> listar();

    /**
     * Elimina una cita existente por su identificador.
     *
     * @param id el ID de la cita a eliminar
     */
    void eliminar(Integer id);
}
