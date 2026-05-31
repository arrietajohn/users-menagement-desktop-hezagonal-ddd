# Sistema de Gestión de Usuarios y Censo Nacional (Arquitectura Hexagonal & DDD)

Este repositorio contiene la integración de dos módulos completos bajo una **Arquitectura Hexagonal estricta** y **Diseño Orientado al Dominio (DDD)** en Java 17. 

1. **Módulo del Profesor (Gestión de Usuarios)**: Código base inalterado para gestionar usuarios.
2. **Módulo CEA (Censo Nacional - Municipio)**: Nuestro desarrollo independiente para la actividad de aprendizaje, implementado sin interferir con el módulo original.

> **Autora del Módulo Censo:** Rosary Carmona  
> **Asignaturas:** Desarrollo de Software / Base de Datos I  

---

## 👨‍🏫 PARTE 1: Módulo del Profesor (Gestión de Usuarios)

Aplicación de consola (Desktop CLI) que gestiona usuarios del sistema. 
- **Tecnologías:** Java 17, Maven, MySQL 8, JavaMail, BCrypt, Hibernate Validator.
- **Patrones:** Arquitectura Hexagonal (Ports & Adapters), DDD (Value Objects, Domain Events).
- **Casos de Uso Originales:** `CreateUser`, `UpdateUser`, `DeleteUser`, `FindUserById`, `ListUsers`, `Login`.
- **Ejecución Original:** Invocado a través de `com.jcaa.usersmanagement.Main`.
- **Estado Actual:** 100% conservado, intacto e independiente en su propio paquete `com.jcaa.usersmanagement`. Su base de datos (`crud_usuarios`) y plantillas HTML de correo (`user-created.html`) permanecen sin modificaciones.

---

## 👩‍💻 PARTE 2: Nuestro Desarrollo (Censo Nacional - CEA)

Como respuesta a la rúbrica de la Actividad de Aprendizaje, se desarrolló de cero el módulo **Censo Nacional** enfocado en la entidad **Municipio**. 

### Arquitectura y Desacoplamiento
Para garantizar las mejores prácticas, el módulo de Censo fue construido **aislando por completo** su ejecución.
- Se implementó un `MainCenso` (`com.rcarmona.censo.MainCenso`) exclusivo.
- Contenedor de inyección manual propio (`CensoDependencyContainer`) y un gestor de entrada/salida aislado (`CensoConsoleIO`).
- **Archivo de Propiedades Independiente:** Censo usa `censo_application.properties` con credenciales y configuración SMTP propias.
- **Templates de Correo Propios:** `municipio-created.html`, `municipio-updated.html`, `municipio-deleted.html`, etc.

### Base de Datos y Normalización (Rúbrica BD I)
Para cumplir con la rúbrica de Bases de Datos I, se diseñó el script `censo_schema.sql` modelado en la 3FN, compuesto por la jerarquía completa: `provincia` -> `municipio` -> `zona_geografica` -> `vivienda` -> `hogar` -> `persona`. La aplicación gestiona el CRUD del `municipio` dentro de este ecosistema geográfico.

### Casos de Uso del Censo (CRUDL + Extras)
1. `CreateMunicipioUseCase`: Registrar municipio (valida inmutabilidad y formatea código único).
2. `GetMunicipioByIdUseCase`: Búsqueda por ID.
3. `UpdateMunicipioUseCase`: Modificar municipio (restringe alterar invariantes del negocio).
4. `DeleteMunicipioUseCase`: Eliminación de municipio (emite evento de dominio).
5. `GetAllMunicipiosUseCase`: Listado completo.
6. `CountMunicipiosUseCase`: Obtener total estadístico (Requisito Extra).
7. `FindMunicipiosByProvinciaUseCase`: Filtrar por provincia (Requisito Extra).
8. `SearchMunicipiosByNameUseCase`: Búsqueda parcial útil para UI (Requisito Extra).

---

## 🧠 PARTE 3: Análisis Arquitectónico y Teoría (Fase 3 de la Rúbrica)

### Arquitectura Hexagonal y DDD
- **Arquitectura Hexagonal:** Resuelve el "Acoplamiento Fuerte". Separa el núcleo de la aplicación de las tecnologías externas, garantizando que el dominio no dependa de MySQL ni de la interfaz de consola, haciéndolo 100% testeable.
- **DDD (Domain-Driven Design):** Enfoque centrado en modelar el negocio real. 
  - **Dominio:** Núcleo puro del software sin frameworks.
  - **Entidad:** Modelo que cambia en el tiempo (Ej. `MunicipioModel`).
  - **Value Object:** Objetos inmutables que encapsulan reglas atómicas (Ej. `CodigoUnico`, que exige longitud de 5 dígitos).

### Puertos, Adaptadores y Capas
- **Capa de Aplicación:** Directora de orquesta. Define los **Puertos de Entrada** (*Use Cases*) para recibir comandos, y los **Puertos de Salida** (*Out Ports*) para exigir operaciones (ej. persistir). Usa **DTOs** para evitar exponer los modelos al exterior, y **Mappers** para traducirlos.
- **Capa de Infraestructura:** Contiene los **Adaptadores**. `MunicipioRepositoryMySQL` implementa el puerto de salida para guardar en MySQL. `MunicipioController` implementa el puerto de entrada para recibir instrucciones por consola.

### Principios SOLID Aplicados
1. **SRP (Responsabilidad Única):** Cada servicio (ej. `CreateMunicipioService`) realiza una y solo una tarea.
2. **OCP (Abierto/Cerrado):** Uso de handlers en la consola para extender funciones sin modificar el menú base.
3. **DIP (Inversión de Dependencias):** La capa de Aplicación depende de interfaces abstractas (`SaveMunicipioPort`) y no de repositorios concretos.

### Patrones de Diseño
1. **Data Transfer Object (DTO):** Aísla la capa de presentación de la capa de dominio.
2. **Mapper Pattern:** Traduce los objetos JPA a Dominio y el Dominio a DTOs.
3. **Dependency Injection:** Se usa inyección manual a través del contenedor centralizado.

---

## 💿 PARTE 4: Script SQL (Jerarquía en 3FN)

```sql
CREATE DATABASE IF NOT EXISTS 7502523003_9_censo_nacional CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE 7502523003_9_censo_nacional;

-- 1. Tabla Provincia
CREATE TABLE IF NOT EXISTS provincia (
    id_provincia INT AUTO_INCREMENT PRIMARY KEY, 
    nombre_provincia VARCHAR(100) NOT NULL
);

-- 2. Tabla Municipio (CRUD Central)
CREATE TABLE IF NOT EXISTS municipio (
    id_municipio INT AUTO_INCREMENT PRIMARY KEY, 
    nombre_municipio VARCHAR(100) NOT NULL, 
    id_provincia INT NOT NULL, 
    CONSTRAINT fk_municipio_provincia FOREIGN KEY (id_provincia) REFERENCES provincia(id_provincia) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- 3. Tablas relacionales complementarias
CREATE TABLE IF NOT EXISTS zona_geografica (
    id_zona INT AUTO_INCREMENT PRIMARY KEY, 
    nombre_zona VARCHAR(50) NOT NULL, 
    id_municipio INT NOT NULL,
    CONSTRAINT fk_zona_municipio FOREIGN KEY (id_municipio) REFERENCES municipio(id_municipio)
);
CREATE TABLE IF NOT EXISTS vivienda (
    id_vivienda BIGINT AUTO_INCREMENT PRIMARY KEY, 
    direccion VARCHAR(255), 
    tipo_vivienda VARCHAR(50) NOT NULL, 
    id_zona INT NOT NULL,
    CONSTRAINT fk_vivienda_zona FOREIGN KEY (id_zona) REFERENCES zona_geografica(id_zona)
);
```

---

## 🚀 PARTE 5: Historial de Construcción (Commits Atómicos)

Para asegurar las mejores prácticas en Git (GitFlow), se trabajó sobre la rama aislada `feature/crudl-municipio` realizando commits granulares, con prefijos descriptivos (Conventional Commits) que explican el *qué*, el *por qué* y el *para qué*:

### Fase 1: Capa de Dominio (Núcleo)
- `feat(domain): modelar entidad central MunicipioModel para representar la estructura base del sistema de censo`
- `feat(domain): crear Value Objects inmutables para blindar la validación y encapsular reglas de negocio del Municipio`
- `feat(domain): definir Excepciones personalizadas para el dominio garantizando respuestas semánticas ante fallos de negocio`
- `feat(domain): implementar Eventos de Dominio y Enums para propagar cambios de estado y categorizar los municipios`
- `test(domain): desarrollar suite de pruebas unitarias para el Dominio asegurando el 100% de cobertura en la lógica core`

### Fase 2: Puertos, Adaptadores y Casos de Uso (Aplicación e Infraestructura)
- `feat(infrastructure): diseñar script SQL en 3FN para la persistencia estructural de toda la jerarquía geográfica del censo`
- `feat(application): definir puertos de salida (Out Ports) para abstraer el almacenamiento y notificaciones (Inversión de Dependencias)`
- `feat(application): definir puertos de entrada (Use Cases) definiendo los contratos de las operaciones permitidas por el sistema`
- `feat(infrastructure): implementar repositorio MySQL para Municipio conectando los adaptadores de persistencia físicos`
- `feat(infrastructure): desarrollar Mappers de persistencia y Excepciones para transformar entidades JPA a Modelos de Dominio sin acoplarlos`
- `test(infrastructure): verificar adaptadores de persistencia comprobando el mapeo correcto entre base de datos y dominio`
- `feat(application): implementar DTOs y Mappers de aplicación aislando el modelo de dominio de las capas externas`
- `feat(application): implementar servicios orquestadores para operaciones CRUD de Municipio aplicando validaciones y eventos`
- `feat(application): implementar servicios avanzados para búsquedas y generación de estadísticas requeridas en el censo`
- `test(application): desarrollar pruebas exhaustivas con Mockito para validar la lógica de orquestación de todos los servicios`

### Fase 3: Puntos de Entrada, Desacoplamiento y Despliegue
- `feat(infrastructure): crear handlers y menú CLI interactivo permitiendo la manipulación del sistema Censo por terminal`
- `feat(infrastructure): desarrollar controlador de entrada y gestión de I/O aislada para interactuar con la aplicación de forma segura`
- `feat(infrastructure): construir inyector de dependencias (DependencyContainer) exclusivo para aislar el contexto del Censo`
- `feat(infrastructure): establecer MainCenso como punto de entrada independiente asegurando el Principio de Responsabilidad Única respecto al módulo del profesor`
- `feat(infrastructure): configurar plantillas HTML SMTP y properties dedicadas para que el Censo tenga canales de notificación autónomos`
- `feat(infrastructure): orquestar inicialización dinámica de BD e integración con Docker, y configurar métricas JaCoCo en POM`
- `test(domain): aplicar ajustes finales en validaciones y dependencias para satisfacer pruebas del 100% de cobertura`

---

## 🛠️ PARTE 6: Stack Tecnológico

El proyecto está construido bajo los siguientes estándares y herramientas:
- **Lenguaje Base:** Java 17 (Uso de Records, Switch Expressions y sintaxis moderna).
- **Gestor de Dependencias y Construcción:** Maven (`pom.xml`).
- **Persistencia de Datos:** Motores Multi-Soporte (MySQL Connector J 9.3.0 y PostgreSQL 42.7.3). JDBC Puro (sin ORMs pesados) para control absoluto.
- **Testing:** JUnit 5 (Júpiter) y Mockito (5.15.2) para el 100% de aislamiento en las pruebas.
- **Herramientas de Productividad:** Lombok (1.18.42) para reducir el *boilerplate* (Getters, Setters, Constructors).
- **Validación:** Jakarta Validation API e Hibernate Validator para validaciones de formato en DTOs.
- **Documentación:** Todo el módulo está minuciosamente documentado (JavaDoc) indicando la capa arquitectónica de cada archivo para facilitar el análisis del tutor y especificando la función técnica individual.

---

## 🚀 PARTE 7: Configuración y Ejecución (Despliegue Fácil)

### Ejecución Manual desde el IDE (Recomendado para la Sustentación)
El proyecto ha sido diseñado con una estrategia de **"Auto-sanación y Migración Estructural"** para que no requieras pasos complicados en la base de datos antes de grabar tu evidencia:

1. Asegúrate de tener tu servidor de Base de Datos local encendido (MySQL o PostgreSQL). No necesitas crear la base de datos a mano.
2. Abre la clase `src/main/java/com/rcarmona/censo/MainCenso.java` y ejecútala (Botón Play en IntelliJ, Eclipse o VS Code).
3. **Bucle de Conexión Interactivo:** La consola te solicitará:
   - Motor (escribe `mysql` o `postgresql`).
   - Host (ej. `localhost`) y Puerto (ej. `3306` o `5432`).
   - Credenciales (Usuario y Contraseña).
4. Si las credenciales fallan, el programa atrapará el error de forma segura y **te volverá a preguntar** (no se cerrará).
5. **Creación Automática:** Si la base de datos `7502523003_9_censo_nacional` no existe, la creará dinámicamente. Luego revisará si las tablas existen y las ensamblará en el orden relacional correcto. En el último paso revisará campo por campo usando `DatabaseMetaData` e inyectará cualquier columna faltante (Auto-Reparación Estructural).

### Pruebas Unitarias
El proyecto cuenta con una cobertura exhaustiva que aísla el núcleo de las tecnologías externas:
```bash
mvn clean test
```
*(Requiere tener Maven configurado en tu entorno).*
