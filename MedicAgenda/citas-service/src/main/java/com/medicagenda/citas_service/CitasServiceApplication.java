package com.medicagenda.citas_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para el arranque del microservicio de citas médicas.
 * <p>
 * Inicializa el contexto de Spring Boot y configura todos los componentes
 * necesarios para el funcionamiento del servicio de citas.
 * </p>
 *
 * @author Ander
 * @since 2025-06-18
 */
@SpringBootApplication
public class CitasServiceApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(CitasServiceApplication.class, args);
    }

}
