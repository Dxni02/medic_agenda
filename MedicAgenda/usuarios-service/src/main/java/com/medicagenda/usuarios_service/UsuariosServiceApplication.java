package com.medicagenda.usuarios_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que arranca el microservicio de usuarios.
 *
 * <p>
 * Esta clase sirve como punto de entrada para la aplicación Spring Boot,
 * cargando el contexto de Spring, inicializando todos los beans y ejecutando
 * el servidor embebido.
 * </p>
 *
 * <p>
 * El proyecto sigue una arquitectura hexagonal y está diseñado para la gestión
 * de usuarios, permitiendo operaciones CRUD y lógica de negocio específica
 * para roles como pacientes, médicos y administradores.
 * </p>
 *
 * @author Ander
 * @since 1.0
 */
@SpringBootApplication
public class UsuariosServiceApplication {

	/**
	 * Método principal que lanza la aplicación.
	 *
	 * @param args argumentos de línea de comandos 
	 */
	public static void main(String[] args) {
		SpringApplication.run(UsuariosServiceApplication.class, args);
	}
}
