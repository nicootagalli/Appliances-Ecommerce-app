# 🛒 E-Commerce de Electrodomésticos (Arquitectura de Microservicios)

## 📌 Descripción general

Este proyecto es una aplicación backend para una plataforma de comercio electrónico especializada en electrodomésticos.  
Está construida utilizando una **arquitectura de microservicios** con Java y Spring Boot, siguiendo buenas prácticas de escalabilidad, modularidad y mantenibilidad.

El sistema permite la gestión de:

* Usuarios
* Productos
* Carritos
* Ventas

Cada responsabilidad está separada en servicios independientes que se comunican entre sí.

---

## 🧱 Arquitectura

La aplicación está compuesta por los siguientes microservicios:

* **user-service** → Gestiona usuarios
* **product-service** → Gestiona productos y stock
* **cart-service** → Maneja carritos de compra
* **sale-service** → Procesa ventas
* **eureka-server** → Descubrimiento de servicios

### 🔄 Comunicación

* APIs REST
* Clientes OpenFeign para comunicación entre servicios
* Descubrimiento de servicios usando Eureka

---

## ⚙️ Tecnologías utilizadas

* Java 17+
* Programación funcional (Streams, Optional, Lambdas)
* Spring Boot
* Spring Data JPA
* Spring Cloud (Eureka, OpenFeign)
* MySQL
* Maven
* Git & GitHub
* Postman

---

## 🚀 Cómo ejecutar el proyecto

1. Iniciar el **Eureka Server**
2. Ejecutar cada microservicio:
   * user-service
   * product-service
   * cart-service
   * sale-service
3. Verificar que todos los servicios estén registrados en Eureka:
   http://localhost:8761

---


---

## 📡 Funcionalidades principales

### 👤 Gestión de usuarios

* Crear usuarios
* Validar la existencia de usuarios mediante API

### 📦 Gestión de productos

* Operaciones CRUD para productos
* Gestión de stock
* Acceso externo para validación y precios

### 🛒 Sistema de carrito

* Agregar productos al carrito
* Validar disponibilidad de stock (pre-chequeo)
* Calcular el total dinámicamente
* Uso de precio “snapshot” (precio al momento de agregar)

### 💰 Sistema de ventas

* Crear ventas basadas en datos del carrito
* Obtener información del carrito dinámicamente
* Orquestar el flujo de compra entre servicios

✅ Manejo de errores y respuestas HTTP  
- Uso de `ResponseEntity` para construir respuestas HTTP personalizadas  
- Implementación de manejo global de excepciones mediante `@ControllerAdvice`  
- Clase centralizada `GlobalExceptionHandler`  
- Respuestas consistentes con códigos HTTP adecuados (`200`, `201`, `400`, `404`, etc.)  
- Validaciones de negocio con excepciones específicas  
---

## 🧠 Decisiones de diseño

* **Aislamiento de microservicios**: Cada servicio posee sus propios datos
* **Uso de DTOs**: Evita exponer entidades internas
* **Capa de mapeo (Mapper)**: Separación clara de responsabilidades
* **Manejo centralizado de excepciones**
* **Comunicación entre APIs**: Uso de clientes Feign

---

## ⚡ Uso de programación funcional

Este proyecto incorpora principios de programación funcional en Java para mejorar la legibilidad, seguridad y mantenibilidad del código.

Usos principales:

- **Streams API** para procesamiento de colecciones
- **Optional** para evitar problemas relacionados con valores nulos
- **Expresiones lambda** para una lógica más clara y expresiva
- Estilo declarativo sobre bucles imperativos cuando es posible

Estas prácticas ayudan a reducir código repetitivo y hacen que la lógica de negocio sea más concisa y expresiva.

---

## 📬 Pruebas de API

Se incluye una colección de Postman para probar todos los endpoints fácilmente.

Pasos:

1. Importar la colección en Postman
2. Ejecutar las requests en orden (User → Product → Cart → Sale)

---

## 🚧 Trabajo en progreso

Actualmente se está implementando:

* Sistema de gestión de stock
* Actualizaciones atómicas de stock en `product-service`
* Manejo de concurrencia para compras simultáneas

Esta funcionalidad se está desarrollando en la rama: stock-management

* API Gateway
* Circuit Breaker (Resilience4j)
* Config Server
* Autenticación y autorización (Spring Security + JWT)
* Contenerización con Docker
---


---

## 🧠 Mejoras futuras

* Autenticación y autorización (Spring Security + JWT)

---
