# 🍕 Platzi Pizza - MVP API
A modern RESTful API for a pizza restaurant built with Java 21, Spring Boot 3.x, and MySQL. This project demonstrates best practices in JPA entity relationships, custom repository queries, and stateless JWT security.

## 🚀 Technologies
Language: Java 21 (LTS)

Framework: Spring Boot 3.3.x

Build Tool: Gradle 8.10+

Database: MySQL 8.0

Security: Spring Security + Auth0 JWT

Utilities: Lombok, Jakarta Persistence (JPA)

## 🛠️ Key Features
Pizza Management: Full CRUD for pizza types, orders, and customers.

Order System: Complex one-to-many relationships between Orders and Order Items.

Advanced Queries: Custom Spring Data JPA methods for filtering by price, description, and availability.

Stateless Security: Custom JWT authentication filter with role-based access control (RBAC).

Automated Seeding: Database initialization via data.sql for users, roles, and initial menu.

## 🏗️ Architecture
The project follows a standard N-Tier architecture:

Persistence Layer: JPA Entities and Repositories (ListCrudRepository).

Service Layer: Business logic and data orchestration.

Web Layer: REST Controllers with modern Security Filter Chain configuration.
