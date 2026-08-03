🛒 E‑Commerce de Electrodomésticos (Microservicios)
📌 Descripción
Backend en Java 17 + Spring Boot con arquitectura de microservicios.
Permite gestionar usuarios, productos, carritos y ventas, cada uno en su propio servicio.

🧱 Arquitectura y Patrones
La aplicación está compuesta por los siguientes microservicios:

config-server → Centralización de configuraciones

eureka-server → Descubrimiento de servicios

api_gateway → Entrada única a los microservicios

user-service → Usuarios

product-service → Productos y stock

cart-service → Carritos

sale-service → Ventas

🔄 Comunicación
APIs REST

Clientes OpenFeign

Descubrimiento de servicios con Eureka

📐 Patrones aplicados
Service Registry & Service Discovery (Eureka)

Load Balancing

Circuit Breaker

API Gateway

Config Server

⚙️ Tecnologías
Java 17, Spring Boot, Spring Data JPA, Spring Cloud (Eureka, Feign), MySQL, Maven, Git/GitHub, Postman.

🚀 Ejecución
Crear las bases de datos necesarias en MySQL con los nombres correspondientes (ej: service-user, service-product, service-cart, service-sale), usuario root y contraseña vacía.

Iniciar el Config Server.

Iniciar el Eureka Server.

Iniciar el Api Gateway.

Ejecutar cada microservicio.

Verificar en http://localhost:8761 que estén registrados.

📡 Funcionalidades
Usuarios: creación y validación

Productos: CRUD + stock

Carrito: agregar productos, validar stock, calcular total

Ventas: crear ventas desde carritos, orquestar flujo de compra

Manejo de errores con ResponseEntity, GlobalExceptionHandler y códigos HTTP consistentes.

🧠 Diseño
Microservicios aislados con sus propios datos

Uso de DTOs y Mappers

Excepciones centralizadas

Comunicación entre APIs con Feign

⚡ Programación funcional
Streams, Optional y Lambdas para código más claro y conciso.

📬 Pruebas
Colección Postman incluida para probar endpoints en orden: User → Product → Cart → Sale.

Proximas mejoras:
- Seguridad con JWT.
- Docker.