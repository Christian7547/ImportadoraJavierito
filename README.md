# 🏪 Importadora Javierito

Comprehensive management system for importers developed with modern technologies, focused on efficient inventory administration, sales and commercial operations.

## 📋 Table of Contents

- [Features](#-features)
- [Architecture](#-architecture)
- [Technologies](#-technologies)
- [Prerequisites](#-prere)
- [Configuration](#️-configuration)

## ✨ Features

### Main Functionalities
- 📦 **Inventory Management**: Complete control of products, stock and categories
- 💰 **Sales System**: Order processing and billing
- 🔐 **Authentication and Authorization**: Secure system with JWT
- 🔄 **RESTful API**: Web services for integration with other systems

### Technical Features
- ⚡ **High Performance**: Optimized to handle large volumes of data
- 🛡️ **Security**: Implementation of security best practices

## 🏗️ Architecture

The API follows a Hexagonal Architecture (Ports and Adapters) pattern, providing a clean separation of concerns and high testability:

```
┌─────────────────────────────────────────────────────────────┐
│                     Hexagonal Architecture                  │
│                                                             │
│  ┌─────────────────┐    ┌─────────────────┐    ┌──────────┐ │
│  │   Adapters      │    │   Application   │    │  Domain  │ │
│  │   (Input/       │◄──►│     Core        │◄──►│  (Core)  │ │
│  │   Output)       │    │                 │    │          │ │
│  │                 │    │                 │    │ - Models │ │
│  │ - REST API      │    │ - Services      │    │ - Rules  │ │
│  │ - Database      │    │ - Ports         │    │          │ │
│  │ - Security      │    │                 │    │          │ │
│  └─────────────────┘    └─────────────────┘    └──────────┘ │
└─────────────────────────────────────────────────────────────┘
```

### Application Layers

#### Backend (Spring Boot)
```
├── Controller Layer    # REST Endpoints
├── Service Layer      # Business Logic
├── Repository Layer   # Data Access
├── Entity Layer       # Data Models
├── Security Layer     # Authentication/Authorization
└── Configuration      # Spring Configurations
```

## 🛠️ Technologies

### Backend
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)

### Development Tools
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)

## ✅ Prerequisites

Before starting, make sure you have installed:

- **Java 23+** - [Download](https://adoptium.net/)
- **PostgreSQL 14+** - [Download](https://www.postgresql.org/download/)
- **Git** - [Download](https://git-scm.com/)
- **Maven 3.8+** - [Download](https://maven.apache.org/download.cgi)

## ⚙️ Configuration

### Backend (application.properties)
```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/importadora_javierito
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=your_very_secure_jwt_secret
jwt.expiration=86400000

# Server Configuration
server.port=8080
```
