# 🛒 Appliances E-Commerce (Microservices Architecture)

## 📌 Overview

This project is a backend application for an e-commerce platform specialized in home appliances.
It is built using a **microservices architecture** with Java and Spring Boot, following best practices for scalability, modularity, and maintainability.

The system allows management of:

* Users
* Products
* Carts
* Sales

Each responsibility is separated into independent services that communicate with each other.

---

## 🧱 Architecture

The application is composed of the following microservices:

* **user-service** → Manages users
* **product-service** → Manages products and stock
* **cart-service** → Handles shopping carts
* **sale-service** → Processes sales
* **eureka-server** → Service discovery

### 🔄 Communication

* REST APIs
* OpenFeign clients for inter-service communication
* Service discovery using Eureka

---

## ⚙️ Technologies Used

* Java 17+
* Functional Programming (Java Streams, Optional, Lambdas)
* Spring Boot
* Spring Data JPA
* Spring Cloud (Eureka, OpenFeign)
* MySQL
* Maven
* Git & GitHub
* Postman

---

## 🚀 How to Run the Project

1. Start the **Eureka Server**
2. Run each microservice:

   * user-service
   * product-service
   * cart-service
   * sale-service
3. Ensure all services are registered in Eureka:

```
http://localhost:8761
```

---

## 📡 Main Features

### 👤 User Management

* Create users
* Validate user existence via API

### 📦 Product Management

* CRUD operations for products
* Stock management
* External access for validation and pricing

### 🛒 Cart System

* Add products to cart
* Validate stock availability (pre-check)
* Calculate total dynamically
* Uses snapshot pricing (price at time of adding)

### 💰 Sales System

* Create sales based on cart
* Fetch cart data dynamically
* Orchestrates purchase flow between services

---

## 🧠 Design Decisions

* **Microservices isolation**: Each service owns its data
* **DTO usage**: Avoid exposing internal entities
* **Mapper layer**: Clean separation between layers
* **Exception handling**: Centralized error handling
* **API communication**: Services communicate via Feign clients

---
## ⚡ Functional Programming Usage

This project incorporates functional programming principles in Java to improve code readability, safety, and maintainability.

Key usages include:

- **Streams API** for collection processing
- **Optional** to avoid null-related issues
- **Lambda expressions** for cleaner and more expressive logic
- Declarative style over imperative loops where possible

These practices help reduce boilerplate code and make business logic more concise and expressive.

---

## 📬 API Testing

A Postman collection is included to test all endpoints easily.

Steps:

1. Import the collection into Postman
2. Run requests in order (User → Product → Cart → Sale)

---

## 🚧 Work in Progress

Currently implementing:

* Stock management system
* Atomic stock updates in `product-service`
* Concurrency handling for simultaneous purchases

This feature is being developed in the branch:

```
stock-management
```

---

## 🧠 Future Improvements

* API Gateway
* Circuit Breaker (Resilience4j)
* Config Server
* Authentication & Authorization (Spring Security + JWT)
* Distributed transactions (Saga pattern)
* Docker containerization

---


## 👨‍💻 Author

Developed as part of a backend portfolio project focused on microservices and scalable systems.
