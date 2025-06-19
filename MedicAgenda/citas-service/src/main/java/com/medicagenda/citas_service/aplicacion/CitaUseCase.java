package com.medicagenda.citas_service.aplicacion;

import com.medicagenda.citas_service.dominio.model.CitaDTO;
import com.medicagenda.citas_service.dominio.model.CitaRequest;
import com.medicagenda.citas_service.dominio.port.in.CitaPortIn;
import com.medicagenda.citas_service.dominio.port.out.CitaPortOut;
import com.medicagenda.citas_service.entity.Cita;
import com.medicagenda.citas_service.entity.Cita.Estado;
import com.medicagenda.citas_service.infraestructura.util.CitaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Caso de uso que implementa la lógica de negocio para la gestión de citas médicas.
 * <p>
 * Encargado de orquestar la creación, consulta, actualización y eliminación de citas,
 * aplicando las validaciones correspondientes.
 * </p>
 *
 * @author Ander
 * @since 2025-06-19
 */
@Service
@RequiredArgsConstructor
public class CitaUseCase implements CitaPortIn {

    private final CitaPortOut citaPortOut;
    private final CitaMapper mapper;

    /**
     * Registra una nueva cita médica.
     *
     * @param request datos de la cita
     * @return cita creada
     */
    @Override
    public CitaDTO crearCita(CitaRequest request) {
        Cita cita = mapper.toEntity(request);
        cita.setEstado(Estado.PENDIENTE);
        return mapper.toDTO(citaPortOut.guardar(cita));
    }

    /**
     * Obtiene una cita por su ID.
     *
     * @param id identificador
     * @return cita encontrada
     */
    @Override
    public CitaDTO obtenerCitaPorId(Integer id) {
        Cita cita = citaPortOut.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
        return mapper.toDTO(cita);
    }

    /**
     * Lista todas las citas registradas.
     *
     * @return lista de citas
     */
    @Override
    public List<CitaDTO> listarCitas() {
        return citaPortOut.listar()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    /**
     * Actualiza una cita existente.
     *
     * @param id      identificador
     * @param request datos actualizados
     * @return cita modificada
     */
    @Override
    public CitaDTO actualizarCita(Integer id, CitaRequest request) {
        Cita existente = citaPortOut.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));

        existente.setFecha(java.time.LocalDate.parse(request.getFecha()));
        existente.setHora(java.time.LocalTime.parse(request.getHora()));
        existente.setEstado(Estado.valueOf(request.getEstado()));
        existente.setObservaciones(request.getObservaciones());
        existente.setPacienteId(request.getPacienteId());
        existente.setMedicoId(request.getMedicoId());

        return mapper.toDTO(citaPortOut.guardar(existente));
    }

    /**
     * Elimina una cita por su ID.
     *
     * @param id identificador
     */
    @Override
    public void eliminarCita(Integer id) {
        citaPortOut.eliminar(id);
    }
}
