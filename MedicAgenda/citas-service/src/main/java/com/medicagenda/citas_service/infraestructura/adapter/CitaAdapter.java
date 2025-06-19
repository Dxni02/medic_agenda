package com.medicagenda.citas_service.infraestructura.adapter;

import com.medicagenda.citas_service.entity.Cita;
import com.medicagenda.citas_service.dominio.port.out.CitaPortOut;
import com.medicagenda.citas_service.infraestructura.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador que implementa la interfaz {@link CitaPortOut} para interactuar con
 * la base de datos
 * mediante JPA a través de {@link CitaRepository}.
 *
 * <p>
 * Este componente forma parte de la capa de infraestructura y sirve de puente
 * entre
 * el dominio y la capa de persistencia.
 * </p>
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
}
