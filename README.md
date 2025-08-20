
<h1 align="center" style="font-size: 50px;">🚀 ForoHub - API REST para Sistema de Foros</h1>
<p align="center"> <img src="./paraREADME/1.jpg" alt="Logo ForoHub"> </p>

---

## 📌 **Índice**
1. [Descripción del Proyecto](#-descripción-del-proyecto)
2. [Estado del Proyecto](#-estado-del-proyecto)
3. [Características del Proyecto](#-características-del-proyecto)
4. [Tecnologías Utilizadas](#-tecnologías-utilizadas)
5. [Acceso al Proyecto](#-acceso-al-proyecto)
6. [Endpoints de la API](#-endpoints-de-la-api)
7. [Personas Desarrolladoras del Proyecto](#-personas-desarrolladoras-del-proyecto)
8. [Licencia](#-licencia)

---

## 📖 **Descripción del Proyecto**

**ForoHub** es una **API REST robusta** desarrollada con **Spring Boot** que permite la gestión completa de un sistema de foros. Esta API proporciona funcionalidades CRUD completas para tópicos, incluyendo autenticación **JWT**, validaciones de negocio y una arquitectura escalable.

---

## 🚀 **Estado del Proyecto**

<h4 align="center">
 ✅🎉 Proyecto Completado y Funcional 🎉✅
</h4>

---

## 🔧 **Características del Proyecto**

### 🔐 **Sistema de Autenticación**
- Autenticación **JWT** segura.
- **Tokens** con expiración configurable.
- Endpoints protegidos y públicos.

### 💾 **Gestión de Tópicos**
- **CREATE**: Crear nuevos tópicos.
- **READ**: Listar y visualizar tópicos.
- **UPDATE**: Actualizar tópicos existentes.
- **DELETE**: Eliminar tópicos (eliminación lógica).

### ⚡ **Funcionalidades Avanzadas**
- Validaciones de negocio integradas.
- Paginación y ordenamiento de resultados.
- Filtrado por **curso** y **año**.
- Manejo de errores detallado.
- Migraciones de base de datos con **Flyway**.

---

## 💻 **Tecnologías Utilizadas**

- **Java 17**: Lenguaje de programación principal.
- **Spring Boot 3.2.4**: Framework principal.
- **Spring Security**: Autenticación y autorización.
- **JWT**: Tokens de autenticación.
- **MySQL 8+**: Base de datos relacional.
- **Flyway**: Migraciones de base de datos.
- **Maven**: Gestión de dependencias.
- **JPA/Hibernate**: ORM y persistencia de datos.

---

## 📁 **Acceso al Proyecto**

1. **Clonar el Repositorio**:
   - `git clone https://github.com/JhairRoussell2/FORO-HUB.git`

2. **Abrir en IDE**:
   - Importar como proyecto Maven en **IntelliJ IDEA** .

---

## 🛠️ **Instrucciones para Ejecutar**

### Prerrequisitos
- **Java JDK 17** o superior.
- **MySQL 8** o superior.
- **Maven 4+**.

### Configuración
1. **Configurar Base de Datos**:
   ```sql
   CREATE DATABASE forohub;
   ```

2. **Configurar Variables de Entorno**:
   - En `application.properties` o como variables de entorno:
     ```bash
     DB_PASSWORD=tu_password_mysql
     JWT_SECRET=tu_clave_secreta_jwt_muy_larga
     ```

### Compilar y Ejecutar:
```bash
mvn clean compile
mvn spring-boot:run
```

### Acceder a la API:
- URL: `http://localhost:8080`

---

## 🌐 **Endpoints de la API**
<p align="center">
  <img src="./paraREADME/Ejm0.jpg" alt="Sí">
</p>


### 🔐 **Autenticación**
- **POST /login**
  - **Content-Type**: application/json
  - Request body:
    ```json
    {
      "email": "admin@forohub.com",
      "password": "123456"
    }
    

    
<p align="center">
  <img src="./paraREADME/Ejm1.jpg" alt="Sí">
</p>

### 📝 **Tópicos**
- **GET /topicos**: Listar todos los tópicos.


<p align="center">
  <img src="./paraREADME/Ejm3.jpg" alt="Sí">
</p>


- **GET /topicos/{id}**: Obtener tópico específico.


<p align="center">
  <img src="./paraREADME/Ejm4.jpg" alt="Sí">
</p>



- **POST /topicos**: Crear nuevo tópico (requiere autenticación).

  
<p align="center">
  <img src="./paraREADME/Ejm2.jpg" alt="Sí">
</p>


- **PUT /topicos/{id}**: Actualizar tópico (requiere autenticación).


<p align="center">
  <img src="./paraREADME/Ejm5.jpg" alt="Sí">
</p>


- **DELETE /topicos/{id}**: Eliminar tópico (requiere autenticación).


<p align="center">
  <img src="./paraREADME/Ejm6.jpg" alt="Sí">
</p>


### 📋 **Ejemplo de Request**
- **POST /topicos**
  - **Authorization**: Bearer eyJhbGciOiJIUzI1NiJ9...
  - **Content-Type**: application/json
  - Request body:
    ```json
    {
      "titulo": "Problema con Spring Security",
      "mensaje": "No puedo configurar JWT correctamente",
      "autorId": 2,
      "cursoId": 1
    }
    ```


### 📋 **Ejemplo de Response**
- Response body:
  ```json
  {
    "id": 1,
    "titulo": "Problema con Spring Security",
    "mensaje": "No puedo configurar JWT correctamente",
    "fechaCreacion": "2024-01-20T10:30:00",
    "status": "NO_RESPONDIDO",
    "autor": "Usuario Ejemplo",
    "curso": "Spring Boot"
  }
  ```

---

## 🧑‍💻 **Personas Desarrolladoras del Proyecto**

| [<img src="https://avatars.githubusercontent.com/u/181286163?v=4" width=115><br><sub>Jhair Roussell Melendez Blas</sub>](https://github.com/JhairRoussell2) |
| :---: |

---

## 📜 **Licencia**

Este proyecto está licenciado bajo la **Licencia MIT**.
