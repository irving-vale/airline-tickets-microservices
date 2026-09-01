# airline-tickets-microservices
# ✈️ Flight Booking & Ticket Generation - Microservices Platform

A cloud-native, distributed microservices ecosystem for flight reservations and ticket management built with **Java 21**, **Spring Boot 3**, and **Spring Cloud**. Designed for high availability, fault tolerance, and scalable containerized deployments.

---

## 🏗️ Architecture & Tech Stack

- **Java 21 & Spring Boot 3** (Virtual Threads / Modern Syntax)
- **Spring Cloud Netflix Eureka** (Service Discovery)
- **Spring Cloud OpenFeign** (Declarative REST Clients)
- **Resilience4j** (Circuit Breaker, Rate Limiter, TimeLimiter)
- **Flyway** (Database Migrations)
- **PostgreSQL** (Relational Storage per Service)
- **Docker / Kubernetes / OpenShift** (Containerization & Orchestration)

---

## 🧩 Microservices Overview

| Microservice | Port | Context Path | Description |
| :--- | :--- | :--- | :--- |
| **`msvc-eureka-server`** | `8761` | `/` | Centralized Service Discovery |
| **`msvc-flights`** | `8081` | `/api/v1` | Manages flights and issues travel tickets |
| **`msvc-users`** | `8083` | `/api/v1` | Customer identity and user profile management |

---

## 🛡️ Resilience & Fault Tolerance Patterns

The system incorporates **Resilience4j** integrated with OpenFeign to ensure high system stability:
- **Circuit Breaker:** Prevents cascade failures when downstream services are unavailable (Count-based sliding window).
- **TimeLimiter:** Cancels calls exceeding max execution thresholds (e.g., 3s timeout).

---

## 🚀 Local Setup & Run

### Prerequisites
- JDK 21
- PostgreSQL 15+
- Maven 3.9+

### Startup Order
1. Run `msvc-eureka-server` (`http://localhost:8761`)
2. Run `msvc-users`
3. Run `msvc-flights`