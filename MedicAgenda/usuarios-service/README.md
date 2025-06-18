
# 🩺 usuarios-service - Microservicio de Gestión de Usuarios

Este microservicio forma parte del sistema **MedicAgenda** y se encarga de la gestión de usuarios, incluyendo médicos, pacientes y administradores. Implementa una arquitectura hexagonal, principios SOLID y está documentado con Swagger y JavaDoc.

---

## 📌 Descripción General

El microservicio permite:
- Registrar usuarios con validaciones según su tipo (PACIENTE, MÉDICO, ADMIN).
- Consultar usuarios por ID o listar todos.
- Actualizar información y roles de usuarios.
- Eliminar usuarios del sistema.
- Controlar reglas de negocio y errores como duplicidad de correos.

---

## 🚀 Cómo ejecutar el proyecto

### Requisitos
- Java 17+
- Maven 3.8+
- MySQL (o H2 para pruebas)

### Pasos
```bash
# Clonar el proyecto
git clone https://github.com/Dxni02/medic_agenda.git

# Navegar al módulo usuarios-service
cd medicagenda/usuarios-service

# Ejecutar la aplicación
./mvnw spring-boot:run
```

Por defecto se levanta en:  
📍 `http://localhost:8080`

---

## 📂 Estructura de carpetas

```
usuarios-service/
├── src/
│   ├── main/
│   │   ├── java/com/medicagenda/usuarios_service/
│   │   │   ├── aplicacion/             # Casos de uso (UseCase)
│   │   │   ├── dominio/                # Modelos y puertos
│   │   │   ├── entity/                 # Entidades JPA
│   │   │   ├── infraestructura/
│   │   │   │   ├── rest/               # Controladores REST
│   │   │   │   ├── adapter/            # Adaptadores de persistencia
│   │   │   │   ├── excepciones/        # Manejadores de errores
│   │   │   │   ├── repository/         # Repositorios JPA
│   │   │   │   └── util/               # Mappers
│   └── resources/
│       └── application.properties
```

---

## 🛠️ Tecnologías usadas

| Tecnología         | Descripción                         |
|--------------------|-------------------------------------|
| Spring Boot 3.5    | Framework principal                 |
| Spring Data JPA    | Persistencia con ORM                |
| MySQL / H2         | Base de datos                       |
| Lombok             | Reducción de boilerplate code       |
| MapStruct          | Conversión DTO ↔ entidad            |
| Swagger (OpenAPI)  | Documentación de la API REST        |
| JavaDoc            | Documentación técnica del código    |
| Maven              | Build y gestión de dependencias     |

---

## 👨‍💻 Autores

- Anderson Martínez Luna  
Estudiante de Ingeniería de Software – Universidad de Cartagena  
GitHub: 
Correo: 



---

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

---

## 📚 Endpoints Swagger

Una vez levantado el proyecto:

📘 OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
