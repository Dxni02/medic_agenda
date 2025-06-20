package com.medicagenda.citas_service.infraestructura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuración de WebClient para llamadas a usuarios-service.
 * <p>
 * Define el bean {@link WebClient} que será utilizado por los adaptadores
 * para comunicarse con el microservicio de usuarios.
 * </p>
 *
 * @author Ander
 * @since 2025-06-19
 */
@Configuration
public class WebClientConfig {

    /**
     * Bean de WebClient configurado con la URL base del microservicio usuarios.
     *
     * @return instancia de WebClient para usuarios-service
     */
    @Bean
    public WebClient webClientUsuarios() {
        return WebClient.builder()
            .baseUrl("http://localhost:8082") // Cambiar por la URL real del microservicio usuarios
            .build();
    }
}
