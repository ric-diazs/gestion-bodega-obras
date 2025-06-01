# Microservicios: Sistema de gestión de bodegas en obra.

|           |                                                 |
|:----------|:------------------------------------------------|
|Grupo      |05                                               |
|Integrantes|Adrian Rivera, Benjamin Llanquiman y Ricardo Díaz|
|Profesor   |Sergio Fuentes Pérez                             |
|Curso      |Desarrollo Full Stack 01 (DSY1103)               |
|Sección    |002V                                             |

En este repositorio se alojan los diez microservicios que, como grupo, decidimos trabajar para la evaluación parcial 2 del curso Desarrollo Full Stack 1.

Los microservicios son parte de nuestro proyecto semestral, que corresponde a un sistema de gestión de bodegas en obras, que son aquellas bodegas que se encuentran al interior de las obras de construcción, las cuales almacenan y distribuyen todas las herramientas y materiales necesarios en las obras que se están realizando. Este sistema, previamente, fue diseñado con una arquitectura monolítica, pero dada las dificultades en su uso, gestión y mantenimiento, se decidió migrarlo a una arquitectura de microservicios.

A continuación se describen los 10 microservicios desarrollados en este proyecto:

- **Gestión de usuarios**: Manejo de perfiles, roles y permisos.
- **Gestión de inventarios**: Administra herramientas y materiales.
- **Registro de entradas y salidas**: Controla movimientos de ítems.
- **Gestión de pedidos**: Solicitudes de materiales/herramientas.
- **Gestión de mantenimiento**: Registra mantenimientos de herramientas.
- **Integración con proveedores**: Envía solicitudes de materiales o herramientas a proveedores externos.
- **Validación de usuarios**: Verifica credenciales.
- **Monitoreo del Sistema**: Supervisa funcionamiento del sistema, enviando registro sobre lo que está pasando.
- **Auditoría y logs**: Registra acciones del sistema.
- **Notificaciones**: Envía alertas por bajo stock o movimientos.

Estos microservicios fueron desarrollados en Java mediante el framework Spring Boot.
