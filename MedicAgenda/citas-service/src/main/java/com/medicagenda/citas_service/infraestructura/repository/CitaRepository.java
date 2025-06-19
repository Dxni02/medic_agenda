package com.medicagenda.citas_service.infraestructura.repository;

import com.medicagenda.citas_service.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Cita}.
 * <p>
 * Proporciona operaciones CRUD sobre la tabla de citas médicas.
 * Permite buscar citas por paciente, médico, fecha y hora para validar disponibilidad.
 * </p>
 *
 * <ul>
 *     <li>Utilizado por {@link com.medicagenda.citas_service.infraestructura.adapter.CitaAdapter} para interactuar con la base de datos.</li>
 *     <li>Permite verificar si un paciente o médico ya tiene una cita en una fecha y hora específicas.</li>
 * </ul>
 *
 * @author Ander
 * @version 1.0
 * @since 2025-06-18
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    /**
     * Busca una cita por paciente, fecha y hora.
     *
     * @param pacienteId identificador del paciente
     * @param fecha      fecha de la cita
     * @param hora       hora de la cita
     * @return un Optional que puede contener la cita encontrada
     */
    Optional<Cita> findByPacienteIdAndFechaAndHora(Integer pacienteId, LocalDate fecha, LocalTime hora);

    /**
     * Busca una cita por médico, fecha y hora.
     *
     * @param medicoId identificador del médico
     * @param fecha    fecha de la cita
     * @param hora     hora de la cita
     * @return un Optional que puede contener la cita encontrada
     */
    Optional<Cita> findByMedicoIdAndFechaAndHora(Integer medicoId, LocalDate fecha, LocalTime hora);
}