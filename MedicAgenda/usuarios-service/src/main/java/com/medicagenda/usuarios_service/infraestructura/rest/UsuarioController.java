package com.medicagenda.usuarios_service.infraestructura.rest;

import com.medicagenda.usuarios_service.dominio.model.UsuarioDTO;
import com.medicagenda.usuarios_service.dominio.model.UsuarioRequest;
import com.medicagenda.usuarios_service.dominio.port.in.UsuarioPortIn;
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
 * Expone los endpoints HTTP para realizar operaciones CRUD (crear, listar,
 * obtener, actualizar, eliminar) sobre usuarios. Actúa como punto de entrada
 * para solicitudes externas relacionadas con la entidad {@code Usuario}.
 * </p>
 * 
 * <p>
 * Los métodos de este controlador delegan la lógica al caso de uso definido
 * en {@link UsuarioPortIn} respetando la arquitectura hexagonal.
 * </p>
 * 
 * @author Ander
 * @since 1.0
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioPortIn usuarioService;

    /**
     * Registra un nuevo usuario.
     *
     * @param request datos del usuario a crear
     * @return respuesta HTTP con mensaje de éxito y el usuario creado
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody UsuarioRequest request) {
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
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    /**
     * Obtiene un usuario específico por su ID.
     *
     * @param id identificador del usuario
     * @return usuario correspondiente al ID proporcionado
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id      identificador del usuario a actualizar
     * @param request nuevos datos del usuario
     * @return respuesta con mensaje de éxito y datos actualizados
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Integer id,
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
     * @param id identificador del usuario a eliminar
     * @return mensaje de confirmación
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(Map.of("mensaje", "Usuario eliminado correctamente"));
    }
}
