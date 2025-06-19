package com.medicagenda.citas_service.infraestructura.adapter;

import com.medicagenda.citas_service.dominio.model.UsuarioTipo;
import com.medicagenda.citas_service.dominio.port.out.CitaUsuarioValidatorPort;
import com.medicagenda.citas_service.dominio.excepciones.MicroUsuariosServiceNoDisponibleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Map;

/**
 * Adaptador que implementa {@link CitaUsuarioValidatorPort}
 * y consulta el microservicio MicroUsuarios-service para obtener el tipo de usuario.
 * <p>
 * Permite validar el rol de un usuario (paciente, médico, admin o desconocido)
 * consultando el microservicio externo de usuarios.
 * </p>
 *
 * <ul>
 *     <li>Devuelve el tipo de usuario según la respuesta del microservicio.</li>
 *     <li>Si el usuario no existe o no tiene tipo, retorna DESCONOCIDO.</li>
 *     <li>Si el MicroUsuarios-service no está disponible, lanza una excepción personalizada.</li>
 * </ul>
 *
 * @author Ander
 * @since 2025-06-19
 */
@Component
@RequiredArgsConstructor
public class CitaUsuarioValidatorAdapter implements CitaUsuarioValidatorPort {

    private final WebClient webClientUsuarios;

    /**
     * Consulta el microservicio MicroUsuarios-service para obtener el tipo de usuario.
     *
     * @param usuarioId ID del usuario
     * @return tipo de usuario si se encuentra, DESCONOCIDO si no
     * @throws MicroUsuariosServiceNoDisponibleException si el microservicio no está disponible
     */
    @Override
    public UsuarioTipo obtenerTipoUsuario(Integer usuarioId) {
        try {
            Map<String, Object> usuario = webClientUsuarios
                    .get()
                    .uri("/api/usuarios/{id}", usuarioId)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                    })
                    .block();

            if (usuario == null || !usuario.containsKey("tipo")) {
                return UsuarioTipo.DESCONOCIDO;
            }

            String tipo = usuario.get("tipo").toString().toUpperCase();
            return switch (tipo) {
                case "PACIENTE" -> UsuarioTipo.PACIENTE;
                case "MEDICO" -> UsuarioTipo.MEDICO;
                case "ADMIN" -> UsuarioTipo.ADMIN;
                default -> UsuarioTipo.DESCONOCIDO;
            };
        } catch (WebClientResponseException.NotFound e) {
            return UsuarioTipo.DESCONOCIDO;
        } catch (Exception e) {
            // Si ocurre cualquier otro error (como conexión rechazada), lanza la excepción personalizada
            throw new MicroUsuariosServiceNoDisponibleException();
        }
    }
}