package com.medicagenda.citas_service.dominio.port.out;

import com.medicagenda.citas_service.entity.Cita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para operaciones relacionadas con el almacenamiento de citas
 * médicas.
 * <p>
 * Define las operaciones que deben ser implementadas por adaptadores de
 * infraestructura, como repositorios o servicios externos.
 * Permite desacoplar la lógica de negocio de la persistencia y facilita la
 * implementación de pruebas y adaptadores alternativos.
 * </p>
 *
 * <ul>
 *     <li>Permite guardar, buscar, listar y eliminar citas.</li>
 *     <li>Incluye métodos para verificar disponibilidad de paciente y médico en una fecha y hora.</li>
 * </ul>
 *
 * @author Ander
 * @since 2025-06-19
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

    /**
     * Verifica si el paciente ya tiene una cita en una fecha y hora determinadas.
     *
     * @param pacienteId ID del paciente
     * @param fecha      fecha de la cita
     * @param hora       hora de la cita
     * @return Optional con la cita si ya existe
     */
    Optional<Cita> buscarPorPacienteFechaHora(Integer pacienteId, LocalDate fecha, LocalTime hora);

    /**
     * Verifica si el médico ya tiene una cita en una fecha y hora determinadas.
     *
     * @param medicoId ID del médico
     * @param fecha    fecha de la cita
     * @param hora     hora de la cita
     * @return Optional con la cita si ya existe
     */
    Optional<Cita> buscarPorMedicoFechaHora(Integer medicoId, LocalDate fecha, LocalTime hora);
}
