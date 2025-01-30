# 🚀 Star Wars API
## 📌 Introducción
Esta es una API Java8 desarrollada con Spring Boot que se integra con la API de Star Wars, implementando autenticación con JWT, pruebas unitarias y de integración y documentación con Swagger.

## 🛠 Tecnologías utilizadas
- Java 8
- Spring Boot (WebFlux, Security, JWT)
- Spring Security
- WebClient
- Maven
- Loombok
- JUnit 5
- Mockito
- Spring Boot Test
- Swagger

## 📂 Estructura
- `config` -> Contiene clases de configuración general
  - `SecurityConfig` -> Configura la seguridad con Spring Security y la autenticación con JWT. Define filtros de seguridad y acceso a endpoints.
  - `WebClientConfig` -> Configuración para la integración con la API de Star Wars.
- `controller` -> Contiene los controladores que manejan las peticiones
  - `AuthController` -> Controlador para el login de la aplicación.
  - `StarWarsController` -> Controlador para manejar las peticiones GET, devueulven las distintas listas de People, Films, Starships y Vehicles.
- `domain` -> 
  - `AuthRequest` -> Estructura para la request del login.
  - `entities` -> Contiene clases de entidad que representan los datos.
  - `mappers` -> Contiene clases que convierten entidades en otro tipo de entidades, como por ejemplo PeopleDetailsResponse a PeopleResponse.
- `security` ->
  - `JwtAuthenticationFilter` -> es un filtro de seguridad en Spring Security que intercepta las peticiones y verifica si el usuario está autenticado.
- `service` -> Contiene la lógica de la aplicación.
  - `StarWarsService` -> Aquí se realizan las llamadas a la API de Star Wars.
  - `JwtService` -> Aquí se realiza la creación y manejo del token.
- `test` -> Contiene los casos de prueba unitarios y de integración para garantizar que la API funcione correctamente.

## 📌 Endpoints
| Método | Endpoint            | Descripción                          | Autenticación |
|--------|---------------------|--------------------------------------|---------------|
| `POST` | `/auth/login`       | Inicia sesión y devuelve el token    | ❌ No requerida |
| `GET`  | `/StarWars/people`  | Devuelve la lista de personajes      | ✅ Requerida |
| `GET`  | `/StarWars/films`   | Devuelve la lista de películas        | ✅ Requerida |
| `GET`  | `/StarWars/starships` | Devuelve la lista de naves espaciales | ✅ Requerida |
| `GET`  | `/StarWars/vehicles` | Devuelve la lista de vehículos        | ✅ Requerida |

## 🔐 Autenticación
- La API utiliza JWT para la autenticación.
- Se ha definido un usuario por defecto:
    - admin / admin123 (Rol ADMIN)
- Para iniciar sesión se debe enviar una petición POST a `/auth/login` donde en el `body-raw-json` va:
```
{
    "username": "admin",
    "password": "admin123"
}
```
Se genera un token el cual se debe colocar en el encabezado de las peticiones GET
`Authorization: Bearer <TOKEN_RECIBIDO>`

## Postman
### 1. **Login - Obtener token**

**URL:**  
`POST https://prueba-tecnica.up.railway.app/auth/login`

**Cuerpo:**
```
{
    "username": "admin",
    "password": "admin123"
}
```

### 2. **Obtener lista de personajes**
**URL:**  
`GET https://prueba-tecnica.up.railway.app/StarWars/people`

### 3. **Obtener lista de naves espacieles**
**URL:**  
`GET https://prueba-tecnica.up.railway.app/StarWars/starships`

### 4. **Obtener lista de películas**
**URL:**  
`GET https://prueba-tecnica.up.railway.app/StarWars/films`

### 5. **Obtener lista de personajes**
**URL:**  
`GET https://prueba-tecnica.up.railway.app/StarWars/vehicles`

**Encabezados para los GET:**

| Key       | Value                 |
|-----------|-----------------------|
| Authorization | Bearer {token}        |
| Content-Type   | application/json      |

**Parámetros de consulta (Query Parameters) para los GET:**

| Key        | Value         | Descripción                             |
|------------|---------------|-----------------------------------------|
| pageNumber | number        | El número de página para la paginación. |
| pageSize   | number        | La cantidad de objetos por página.      |
| id         | List(Integer) | Busqueda por ids.                       |
| name       | String        | Busqueda por name.                      |

## 📖 Documentación en Swagger
Puedes acceder a la documentación de la API en:
🔗 https://prueba-tecnica.up.railway.app/swagger-ui/index.html

## 🧪 Pruebas
La API cuenta con 2 pruebas unitarias y 1 de integración
### 🔹 Pruebas unitarias
Verifican el comportamiento de componentes individuales
- `src/test/java/com/pruebaTecnica/service`
  - `JwtServiceTest` -> Valida que los tokens generedados sean correctos.
  - `StarWarsServiceTest.java` -> Verifica que `getPeople()` realice la llamala a la api correctamente y traiga datos esperados.

### 🔹 Prueba de integración
Valida el flujo completo en el inicio de sesión
- `src/test/java/com/pruebaTecnica/controller`
  - `AuthControllerTest.java`

### ▶️ Ejecución de las pruebas
Para ejecutar todas las pruebas
- `mvn test`

Para ejecutar una prueba especifica
- `mvn -Dtest=StarWarsServiceTest test`