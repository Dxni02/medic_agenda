package com.medicagenda.citas_service.infraestructura.adapter;

import com.medicagenda.citas_service.entity.Cita;
import com.medicagenda.citas_service.dominio.port.out.CitaPortOut;
import com.medicagenda.citas_service.infraestructura.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Adaptador que implementa la interfaz {@link CitaPortOut} para interactuar con
 * la base de datos mediante JPA a través de {@link CitaRepository}.
 *
 * <p>
 * Este componente forma parte de la capa de infraestructura y sirve de puente
 * entre el dominio y la capa de persistencia.
 * </p>
 *
 * <ul>
 *     <li>Permite guardar, buscar, listar y eliminar citas médicas.</li>
 *     <li>Implementa métodos para verificar disponibilidad de paciente y médico en una fecha y hora.</li>
 * </ul>
 *
 * @author Ander
 * @version 1.0
 * @since 2025-06-18
 */
@Repository
@RequiredArgsConstructor
public class CitaAdapter implements CitaPortOut {

    private final CitaRepository citaRepository;

    /**
     * Guarda una cita en la base de datos.
     *
     * @param cita la entidad cita a persistir.
     * @return la cita guardada con su ID generado.
     */
    @Override
    public Cita guardar(Cita cita) {
        return citaRepository.save(cita);
    }

    /**
     * Busca una cita por su identificador.
     *
     * @param id identificador de la cita.
     * @return un {@link Optional} que puede contener la cita encontrada.
     */
    @Override
    public Optional<Cita> buscarPorId(Integer id) {
        return citaRepository.findById(id);
    }

    /**
     * Retorna todas las citas almacenadas.
     *
     * @return una lista con todas las citas.
     */
    @Override
    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    /**
     * Elimina una cita según su identificador.
     *
     * @param id el identificador de la cita a eliminar.
     */
    @Override
    public void eliminar(Integer id) {
        citaRepository.deleteById(id);
    }

    /**
     * Busca una cita por paciente, fecha y hora.
     *
     * @param pacienteId identificador del paciente.
     * @param fecha      fecha de la cita.
     * @param hora       hora de la cita.
     * @return un {@link Optional} que puede contener la cita encontrada.
     */
    @Override
    public Optional<Cita> buscarPorPacienteFechaHora(Integer pacienteId, LocalDate fecha, LocalTime hora) {
        return citaRepository.findByPacienteIdAndFechaAndHora(pacienteId, fecha, hora);
    }

    /**
     * Busca una cita por médico, fecha y hora.
     *
     * @param medicoId identificador del médico.
     * @param fecha    fecha de la cita.
     * @param hora     hora de la cita.
     * @return un {@link Optional} que puede contener la cita encontrada.
     */
    @Override
    public Optional<Cita> buscarPorMedicoFechaHora(Integer medicoId, LocalDate fecha, LocalTime hora) {
        return citaRepository.findByMedicoIdAndFechaAndHora(medicoId, fecha, hora);
    }
}