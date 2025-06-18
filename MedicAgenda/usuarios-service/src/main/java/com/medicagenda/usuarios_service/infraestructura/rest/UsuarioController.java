package com.medicagenda.usuarios_service.infraestructura.rest;

import com.medicagenda.usuarios_service.dominio.model.UsuarioDTO;
import com.medicagenda.usuarios_service.dominio.model.UsuarioRequest;
import com.medicagenda.usuarios_service.dominio.port.in.UsuarioPortIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Controlador REST para la gestión de usuarios.
 *
 * <p>
 * Expone los endpoints HTTP para realizar operaciones CRUD sobre la entidad
 * {@link UsuarioDTO}. Delegando la lógica en el puerto {@link UsuarioPortIn}
 * dentro de la arquitectura hexagonal.
 * </p>
 *
 * @author Ander
 * @since 1.0
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UsuarioController {

    private final UsuarioPortIn usuarioService;

    /**
     * Registra un nuevo usuario.
     *
     * @param request datos del usuario a crear
     * @return respuesta con mensaje de éxito y usuario creado
     */
    @Operation(summary = "Registrar nuevo usuario", description = "Registra un usuario del tipo PACIENTE, MÉDICO u otro.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o violación de reglas"),
            @ApiResponse(responseCode = "409", description = "Correo ya registrado")
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(
            @RequestBody UsuarioRequest request) {
        UsuarioDTO creado = usuarioService.crearUsuario(request);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Usuario registrado exitosamente");
        response.put("usuario", creado);
        return ResponseEntity.status(200).body(response);
    }

    /**
     * Lista todos los usuarios registrados.
     *
     * @return lista de usuarios
     */
    @Operation(summary = "Listar usuarios", description = "Retorna todos los usuarios registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida con éxito")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    /**
     * Obtiene un usuario específico por su ID.
     *
     * @param id identificador del usuario
     * @return usuario correspondiente
     */
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve el usuario correspondiente al ID proporcionado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtener(
            @Parameter(description = "ID del usuario a consultar", required = true) @PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id      identificador del usuario a actualizar
     * @param request nuevos datos del usuario
     * @return respuesta con mensaje y usuario actualizado
     */
    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente según su tipo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "Correo duplicado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(
            @Parameter(description = "ID del usuario a actualizar", required = true) @PathVariable Integer id,
            @RequestBody UsuarioRequest request) {

        UsuarioDTO actualizado = usuarioService.actualizarUsuario(id, request);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Usuario actualizado correctamente");
        response.put("usuario", actualizado);
        return ResponseEntity.ok(response);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id identificador del usuario
     * @return mensaje de confirmación
     */
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario existente por su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(
            @Parameter(description = "ID del usuario a eliminar", required = true) @PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(Map.of("mensaje", "Usuario eliminado correctamente"));
    }
}
