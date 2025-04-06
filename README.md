# 🗂️ Task Manager - Backend

Este repositorio contiene el backend del proyecto **Task Manager**, una aplicación tipo Kanban para la gestión de tareas y proyectos. Desarrollado con **Spring Boot** y **MySQL**, proporciona una API RESTful segura con autenticación basada en JWT.

---

## 🚀 Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Spring Security (JWT)  
- MySQL  
- Maven  
- Lombok  

---

## 📐 Arquitectura

El proyecto sigue una **arquitectura en capas**:

- `controller/`: gestiona las peticiones HTTP
- `service/`: contiene la lógica de negocio
- `repository/`: manejo de acceso a datos con JPA

---

## 🔐 Autenticación

Se implementó autenticación y autorización usando **JWT (JSON Web Tokens)**.  
Los usuarios deben autenticarse para acceder a los endpoints protegidos.

---

## 🛠️ Configuración local

### Requisitos previos

- Java 17+
- Maven
- MySQL
