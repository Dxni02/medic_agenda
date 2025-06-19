package com.medicagenda.citas_service.infraestructura.repository;

import com.medicagenda.citas_service.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Cita}.
 * Proporciona operaciones CRUD sobre la tabla de citas médicas.
 *
 * <p>
 * Este componente es utilizado por
 * {@link com.medicagenda.citas_service.infraestructura.adapter.CitaAdapter}
 * para interactuar con la base de datos.
 * </p>
 *
 * @author Ander
 * @version 1.0
 * @since 2025-06-18
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    Optional<Cita> findByPacienteIdAndFechaAndHora(Integer pacienteId, LocalDate fecha, LocalTime hora);

    Optional<Cita> findByMedicoIdAndFechaAndHora(Integer medicoId, LocalDate fecha, LocalTime hora);
}