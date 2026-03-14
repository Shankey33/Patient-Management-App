# Enterprise Patient Management System: Microservices Architecture

This repository contains a **production-ready, enterprise-level Patient Management System** built using **Java Spring Boot microservices**.  

The ecosystem leverages:

- **Docker** for containerization  
- **PostgreSQL** for relational data storage  
- **Apache Kafka** for event-driven asynchronous communication  

High-performance internal service-to-service communication is implemented using **gRPC with Protocol Buffers over HTTP/2**, while **Spring Cloud Gateway** acts as the centralized API Gateway for routing and cross-cutting concerns.

---

# 🏗 Architectural Overview

The system demonstrates modern **microservices architecture patterns**, including:

- **Inversion of Control (IoC)**
- **Dependency Injection**
- **Infrastructure as Code (IaC)**
- **Event-Driven Architecture**

---

# Core Services

### API Gateway
A **reactive gateway** that serves as the **single entry point** for all client requests.

Responsibilities:

- Request routing
- Centralized JWT validation
- Cross-cutting concerns such as logging and security

---

### Authentication Service

Handles authentication and authorization logic.

Responsibilities:

- Managing user credentials
- **BCrypt password hashing**
- Issuing **JSON Web Tokens (JWT)** for secure API access

---

### Patient Service

The **core domain service** responsible for patient management.

Responsibilities:

- CRUD operations for patient records
- Making **synchronous gRPC calls** to the Billing Service
- Publishing **patient_created events** to Kafka

---

### Billing Service

A **high-performance service** responsible for managing patient billing accounts.

Responsibilities:

- Processing account creation requests
- Exposing a **gRPC server stub** for internal service communication

---

### Analytics Service

An **asynchronous consumer** that processes events from Kafka.

Responsibilities:

- Consuming `patient_created` events
- Performing **audit logging**
- Running analytics and downstream processing

---

# Communication Patterns

### Synchronous Communication

Used when an **immediate response is required**.

Technologies:

- **REST APIs** → Client → API Gateway → Microservices
- **gRPC** → Service-to-service communication (low latency)

---

### Asynchronous Communication

Used for **event-driven workflows**.

Technology:

- **Apache Kafka**

Benefits:

- Services remain loosely coupled
- Consumers process events independently
- Primary service execution is **not blocked**

Example flow:

Patient Service
↓
Kafka Topic (patient_created)
↓
Analytics Service
Notifications Service
Audit Service


---

# 🛠 Tech Stack

### Backend
- **Java 21 (LTS)**
- **Spring Boot 3**

### Messaging
- **Apache Kafka**

### Internal RPC
- **gRPC**
- **Protocol Buffers (Proto3)**

### Security
- **Spring Security**
- **JWT Authentication**
- **BCrypt password hashing**

### Database
- **PostgreSQL** (primary relational database)
- **H2** (in-memory database for rapid local development)

### Infrastructure
- **AWS CDK (Java)**  
- Generates **CloudFormation templates**

### Testing
- **JUnit 5**
- **Rest Assured**

Used for **integration testing across services**.

---
