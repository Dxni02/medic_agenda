package com.medicagenda.citas_service.aplicacion;

import com.medicagenda.citas_service.dominio.model.CitaDTO;
import com.medicagenda.citas_service.dominio.model.CitaRequest;
import com.medicagenda.citas_service.dominio.model.UsuarioTipo;
import com.medicagenda.citas_service.dominio.port.in.CitaPortIn;
import com.medicagenda.citas_service.dominio.port.out.CitaPortOut;
import com.medicagenda.citas_service.dominio.port.out.CitaUsuarioValidatorPort;
import com.medicagenda.citas_service.dominio.excepciones.CitaNoEncontradaException;
import com.medicagenda.citas_service.dominio.excepciones.PacienteNoValidoException;
import com.medicagenda.citas_service.dominio.excepciones.MedicoNoValidoException;
import com.medicagenda.citas_service.dominio.excepciones.CitaDuplicadaException;
import com.medicagenda.citas_service.entity.Cita;
import com.medicagenda.citas_service.entity.Cita.Estado;
import com.medicagenda.citas_service.infraestructura.util.CitaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Implementación de los casos de uso para la gestión de citas médicas.
 * <p>
 * Esta clase contiene la lógica de negocio para crear, consultar, actualizar y eliminar citas,
 * incluyendo validaciones de tipo de usuario y disponibilidad de horarios.
 * </p>
 *
 * <ul>
 *     <li>Valida que los IDs de paciente y médico correspondan a usuarios válidos.</li>
 *     <li>Evita duplicidad de citas para un mismo paciente o médico en la misma fecha y hora.</li>
 *     <li>Lanza excepciones personalizadas para errores de negocio.</li>
 * </ul>
 *
 * Implementa el puerto de entrada {@link CitaPortIn} y utiliza los puertos de salida
 * {@link CitaPortOut} y {@link CitaUsuarioValidatorPort} para la persistencia y validación.
 *
 * @author Ander
 * @version 1.0
 * @since 2025-06-18
 */
@Service
@RequiredArgsConstructor
public class CitaUseCase implements CitaPortIn {

    private final CitaPortOut citaPortOut;
    private final CitaMapper mapper;
    private final CitaUsuarioValidatorPort citaUsuarioValidator;

    /**
     * Crea una nueva cita médica, validando el tipo de usuario y la disponibilidad.
     *
     * @param request datos de la cita a crear
     * @return DTO de la cita creada
     * @throws PacienteNoValidoException si el pacienteId no corresponde a un paciente válido
     * @throws MedicoNoValidoException si el medicoId no corresponde a un médico válido
     * @throws CitaDuplicadaException si ya existe una cita para el paciente o médico en la misma fecha y hora
     */
    @Override
    public CitaDTO crearCita(CitaRequest request) {
        // Validar tipo de usuario para paciente y médico
        UsuarioTipo tipoPaciente = citaUsuarioValidator.obtenerTipoUsuario(request.getPacienteId());
        UsuarioTipo tipoMedico = citaUsuarioValidator.obtenerTipoUsuario(request.getMedicoId());

        if (tipoPaciente != UsuarioTipo.PACIENTE) {
            throw new PacienteNoValidoException(request.getPacienteId());
        }
        if (tipoMedico != UsuarioTipo.MEDICO) {
            throw new MedicoNoValidoException(request.getMedicoId());
        }

        // Validar disponibilidad de paciente y médico en la fecha y hora
        LocalDate fecha = LocalDate.parse(request.getFecha());
        LocalTime hora = LocalTime.parse(request.getHora());

        if (citaPortOut.buscarPorPacienteFechaHora(request.getPacienteId(), fecha, hora).isPresent()) {
            throw new CitaDuplicadaException("El paciente ya tiene una cita a esa fecha y hora.");
        }
        if (citaPortOut.buscarPorMedicoFechaHora(request.getMedicoId(), fecha, hora).isPresent()) {
            throw new CitaDuplicadaException("El médico ya tiene una cita a esa fecha y hora.");
        }

        Cita cita = mapper.toEntity(request);
        cita.setEstado(Estado.PENDIENTE);
        return mapper.toDTO(citaPortOut.guardar(cita));
    }

    /**
     * Obtiene los datos de una cita específica por su identificador.
     *
     * @param id identificador de la cita
     * @return DTO de la cita encontrada
     * @throws CitaNoEncontradaException si no existe una cita con el ID proporcionado
     */
    @Override
    public CitaDTO obtenerCitaPorId(Integer id) {
        Cita cita = citaPortOut.buscarPorId(id)
                .orElseThrow(() -> new CitaNoEncontradaException(id));
        return mapper.toDTO(cita);
    }

    /**
     * Lista todas las citas existentes en el sistema.
     *
     * @return lista de DTOs de citas
     */
    @Override
    public List<CitaDTO> listarCitas() {
        return mapper.toDTOList(citaPortOut.listar());
    }

    /**
     * Actualiza una cita existente, validando el tipo de usuario y la disponibilidad.
     *
     * @param id identificador de la cita a actualizar
     * @param request nuevos datos para la cita
     * @return DTO de la cita actualizada
     * @throws CitaNoEncontradaException si no existe una cita con el ID proporcionado
     * @throws PacienteNoValidoException si el pacienteId no corresponde a un paciente válido
     * @throws MedicoNoValidoException si el medicoId no corresponde a un médico válido
     * @throws CitaDuplicadaException si ya existe una cita para el paciente o médico en la misma fecha y hora (excepto la actual)
     */
    @Override
    public CitaDTO actualizarCita(Integer id, CitaRequest request) {
        Cita existente = citaPortOut.buscarPorId(id)
                .orElseThrow(() -> new CitaNoEncontradaException(id));

        UsuarioTipo tipoPaciente = citaUsuarioValidator.obtenerTipoUsuario(request.getPacienteId());
        UsuarioTipo tipoMedico = citaUsuarioValidator.obtenerTipoUsuario(request.getMedicoId());

        if (tipoPaciente != UsuarioTipo.PACIENTE) {
            throw new PacienteNoValidoException(request.getPacienteId());
        }
        if (tipoMedico != UsuarioTipo.MEDICO) {
            throw new MedicoNoValidoException(request.getMedicoId());
        }

        // Validar disponibilidad de paciente y médico en la fecha y hora (excepto la cita actual)
        LocalDate fecha = LocalDate.parse(request.getFecha());
        LocalTime hora = LocalTime.parse(request.getHora());

        citaPortOut.buscarPorPacienteFechaHora(request.getPacienteId(), fecha, hora)
                .filter(cita -> !cita.getId().equals(id))
                .ifPresent(cita -> {
                    throw new CitaDuplicadaException("El paciente ya tiene una cita a esa fecha y hora.");
                });

        citaPortOut.buscarPorMedicoFechaHora(request.getMedicoId(), fecha, hora)
                .filter(cita -> !cita.getId().equals(id))
                .ifPresent(cita -> {
                    throw new CitaDuplicadaException("El médico ya tiene una cita a esa fecha y hora.");
                });

        existente.setFecha(fecha);
        existente.setHora(hora);
        existente.setEstado(Estado.valueOf(request.getEstado()));
        existente.setObservaciones(request.getObservaciones());
        existente.setPacienteId(request.getPacienteId());
        existente.setMedicoId(request.getMedicoId());

        return mapper.toDTO(citaPortOut.guardar(existente));
    }

    /**
     * Elimina una cita por su identificador.
     *
     * @param id identificador de la cita a eliminar
     */
    @Override
    public void eliminarCita(Integer id) {
        citaPortOut.eliminar(id);
    }
}