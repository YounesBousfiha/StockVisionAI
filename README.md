# 📦 StockVisionAI

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.9-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)

**An intelligent stock management system powered by AI for predictive analytics and inventory optimization**

[Features](#-features) •
[Architecture](#-architecture) •
[Getting Started](#-getting-started) •
[API Documentation](#-api-documentation) •
[Deployment](#-deployment)

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
- [Configuration](#-configuration)
- [API Documentation](#-api-documentation)
- [Security](#-security)
- [CI/CD Pipeline](#-cicd-pipeline)
- [Docker Deployment](#-deployment)
- [Project Structure](#-project-structure)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🎯 Overview

**StockVisionAI** is an enterprise-grade inventory management system that leverages artificial intelligence to provide predictive analytics, optimize stock levels, and streamline warehouse operations. Built with modern technologies and microservices architecture, it offers real-time insights and intelligent forecasting capabilities.

### Key Highlights

✨ **AI-Powered Predictions** - Utilize DeepSeek AI for sales forecasting and inventory optimization  
🔐 **Enterprise Security** - JWT authentication with HashiCorp Vault for secrets management  
📊 **Real-time Analytics** - Track sales history, stock levels, and warehouse performance  
🏢 **Multi-Warehouse Support** - Manage multiple warehouses with role-based access control  
🚀 **Scalable Architecture** - Containerized deployment with Docker and orchestration support  
📈 **CI/CD Ready** - Automated pipeline with Jenkins and SonarCloud integration

---

## ✨ Features

### Core Functionality

- **Product Management**
  - CRUD operations for products
  - Category and SKU management
  - Warehouse assignment
  - Stock threshold alerts

- **Inventory Control**
  - Real-time stock tracking
  - Multi-warehouse inventory
  - Stock movement history
  - Low stock notifications

- **Sales History**
  - Comprehensive sales tracking
  - Historical data analysis
  - Revenue analytics
  - Sales trends visualization

- **AI-Powered Predictions**
  - Sales forecasting using DeepSeek AI
  - Demand prediction
  - Optimal stock level recommendations
  - Seasonal trend analysis

- **Warehouse Management**
  - Multiple warehouse support
  - Location tracking
  - Capacity management
  - Manager assignment

### Security & Authentication

- JWT-based authentication (Access & Refresh tokens)
- Role-based access control (ADMIN, GESTIONNAIRE)
- Keycloak integration for production environments
- HashiCorp Vault for secure secret management
- Redis-based session management

---

## 🏗 Architecture

StockVisionAI follows a clean architecture pattern with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────┐
│                   Presentation Layer                     │
│          (Controllers, DTOs, Request/Response)          │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────────┐
│                  Application Layer                       │
│            (Services, Mappers, Business Logic)          │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────────┐
│                    Domain Layer                          │
│          (Entities, Repositories, Exceptions)           │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────────┐
│                Infrastructure Layer                      │
│     (Config, Security, AI, Persistence, External APIs)  │
└─────────────────────────────────────────────────────────┘
```

### Key Components

- **MongoDB** - Primary database for document storage
- **Redis** - Caching and session management
- **HashiCorp Vault** - Secrets management
- **Keycloak** - Identity and access management (Production)
- **DeepSeek AI** - AI-powered predictions and analytics

---

## 🛠 Tech Stack

### Backend

- **Java 21** - Latest LTS version with modern features
- **Spring Boot 3.5.9** - Application framework
- **Spring Security** - Authentication & authorization
- **Spring Data MongoDB** - Database integration
- **Spring Data Redis** - Caching layer
- **Spring Cloud Vault** - Secrets management
- **Spring AI** - AI integration framework

### Libraries & Tools

- **Lombok** - Boilerplate code reduction
- **MapStruct** - Type-safe bean mapping
- **JWT (Auth0)** - Token-based authentication
- **Maven** - Dependency management
- **SonarCloud** - Code quality analysis

### DevOps & Infrastructure

- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration
- **Jenkins** - CI/CD automation
- **HashiCorp Vault** - Secret management
- **Keycloak** - OAuth2/OIDC provider

---

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 21** or higher ([Download](https://adoptium.net/))
- **Maven 3.9+** ([Download](https://maven.apache.org/download.cgi))
- **Docker & Docker Compose** ([Download](https://www.docker.com/products/docker-desktop))
- **Git** ([Download](https://git-scm.com/downloads))

Optional (for development):
- **IntelliJ IDEA** or your preferred IDE
- **Postman** or similar API testing tool
- **MongoDB Compass** for database visualization

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/younesbousfiha/StockVisionAI.git
cd StockVisionAI
```

### 2. Start Infrastructure Services

Use Docker Compose to start all required services:

```bash
# Start MongoDB, Redis, Vault, and Keycloak
docker-compose up -d
```

This will start:
- **MongoDB** on port `27018`
- **Redis** on port `6380`
- **Vault** on port `8200`
- **Keycloak** on port `8180`

### 3. Initialize Vault

Initialize HashiCorp Vault with secrets:

#### On Linux/Mac:
```bash
chmod +x init-vault.sh
./init-vault.sh
```

#### On Windows:
```powershell
.\init-vault.ps1
```

The script will configure Vault with necessary secrets (JWT secret, MongoDB URI, Redis config).

### 4. Build the Application

```bash
# Build without tests
mvn clean package -DskipTests

# Build with tests
mvn clean package
```

### 5. Run the Application

#### Development Mode:
```bash
# Using Maven
mvn spring-boot:run

# Using Java
java -jar target/stockvisionai-0.0.1-SNAPSHOT.jar
```

#### Production Mode:
```bash
export SPRING_PROFILES_ACTIVE=prod
java -jar target/stockvisionai-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8080**

---

## ⚙️ Configuration

### Environment Profiles

The application supports multiple profiles:

- **dev** - Development environment (default)
- **prod** - Production environment with Keycloak

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_PROFILES_ACTIVE` | Active profile | `dev` |
| `VAULT_TOKEN` | Vault authentication token | `hvs.nZCiJCqPE37J254yytaHqEaa` |
| `VAULT_HOST` | Vault host | `localhost` |
| `MONGODB_URI` | MongoDB connection string | `mongodb://localhost:27017/stockvisionai` |
| `REDIS_HOST` | Redis host | `localhost` |
| `REDIS_PORT` | Redis port | `6379` |
| `KEYCLOAK_ISSUER_URI` | Keycloak issuer URI (prod) | `http://localhost:8180/realms/stock-realm` |

### Vault Configuration

Secrets are stored in HashiCorp Vault under `secret/stockvisionai`:

```json
{
  "jwt.secret-key": "your-secret-key",
  "spring.data.mongodb.uri": "mongodb://localhost:27017/stockvisionai",
  "spring.data.redis.host": "localhost",
  "spring.data.redis.port": "6379"
}
```

### Application Properties

Key configurations in `application.yaml`:

```yaml
# JWT Token Expiration
jwt:
  access-token:
    expiration: 900000      # 15 minutes
  refresh-token:
    expiration: 604800000   # 7 days

# DeepSeek AI Configuration
spring.ai:
  deepseek:
    api-key:  API_KEY_HERE
    base-url: https://api.deepseek.com/chat/completions
    model: deepseek-chat
```

---

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securePassword123"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securePassword123"
}

Response:
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer"
}
```

### Product Management

#### Create Product (Admin Only)
```http
POST /api/product
Authorization: Bearer {accessToken}
Content-Type: application/json

{
  "name": "Product Name",
  "description": "Product Description",
  "price": 99.99,
  "sku": "PROD-001",
  "entrepotId": "warehouse-id"
}
```

#### Get All Products
```http
GET /api/product
Authorization: Bearer {accessToken}
```

#### Update Product
```http
PUT /api/product/{id}
Authorization: Bearer {accessToken}
Content-Type: application/json

{
  "name": "Updated Product Name",
  "price": 109.99
}
```

#### Delete Product
```http
DELETE /api/product/{id}
Authorization: Bearer {accessToken}
```

### Stock Management

```http
GET /api/stock                    # Get all stocks
POST /api/stock                   # Create stock entry
GET /api/stock/{id}               # Get stock by ID
PUT /api/stock/{id}               # Update stock
DELETE /api/stock/{id}            # Delete stock
```

### Warehouse (Entrepot) Management

```http
GET /api/entrepot                 # Get all warehouses
POST /api/entrepot                # Create warehouse (Admin)
GET /api/entrepot/{id}            # Get warehouse by ID
PUT /api/entrepot/{id}            # Update warehouse
DELETE /api/entrepot/{id}         # Delete warehouse
```

### Sales History

```http
GET /api/historique-vente         # Get sales history
POST /api/historique-vente        # Record sale
GET /api/historique-vente/{id}    # Get sale by ID
```

### AI Predictions

```http
POST /api/prediction              # Get AI-powered predictions
GET /api/prevision                # Get forecasts
```

### User Management

```http
GET /api/user                     # Get all users (Admin)
GET /api/user/{id}                # Get user by ID
PUT /api/user/{id}                # Update user
DELETE /api/user/{id}             # Delete user
```

### Role-Based Access

- **ADMIN** - Full access to all endpoints
- **GESTIONNAIRE** - Limited to assigned warehouse data

---

## 🔐 Security

### Authentication Flow

1. User registers/logs in via `/api/auth/register` or `/api/auth/login`
2. Server returns JWT access token (15 min) and refresh token (7 days)
3. Client includes access token in `Authorization: Bearer {token}` header
4. Server validates token and checks user roles/permissions

### Security Features

- ✅ JWT-based stateless authentication
- ✅ Password encryption with BCrypt
- ✅ Role-based access control (RBAC)
- ✅ Refresh token rotation
- ✅ Secret management with HashiCorp Vault
- ✅ CORS configuration
- ✅ Method-level security with `@PreAuthorize`
- ✅ Keycloak OAuth2 integration (Production)

### Securing Secrets

**NEVER** commit sensitive data to version control. Use Vault for:
- Database credentials
- JWT secret keys
- API keys
- Third-party service credentials

---

## 🔄 CI/CD Pipeline

The project uses **Jenkins** for continuous integration with the following stages:

### Pipeline Stages

1. **📥 Checkout** - Clone repository
2. **🧪 Build & Analyze** - Compile, test, and analyze with SonarCloud
3. **🐳 Build Docker Image** - Create production-ready container
4. **🧹 Cleanup** - Remove temporary artifacts

### Jenkins Configuration

```groovy
Tools Required:
- Maven 3 (maven3)
- JDK 21 (jdk21)

Credentials:
- sonarcloud-token (SonarCloud authentication)
```

### SonarCloud Integration

Code quality metrics are tracked on SonarCloud:
- Code coverage
- Code smells
- Security vulnerabilities
- Technical debt
- Maintainability rating

---

## 🐳 Deployment

### Docker Deployment

#### Build Docker Image

```bash
docker build -t stockvisionai:latest .
```

#### Run with Docker

```bash
docker run -d \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e VAULT_TOKEN=your-token \
  --name stockvisionai \
  stockvisionai:latest
```

### Docker Compose Deployment

#### Development Environment

```bash
docker-compose -f docker-compose-dev.yml up -d
```

#### Production Environment

```bash
docker-compose -f docker-compose-prod.yml up -d
```

#### Complete Stack

```bash
docker-compose up -d
```

### Multi-Stage Docker Build

The Dockerfile uses multi-stage builds for optimization:

- **Stage 1 (Builder)** - Maven build with dependencies
- **Stage 2 (Runtime)** - Lightweight JRE with security hardening
  - Uses Alpine Linux for minimal footprint
  - Non-root user for enhanced security
  - Optimized layer caching

### Environment-Specific Deployments

| Environment | File | Purpose |
|-------------|------|---------|
| Development | `docker-compose-dev.yml` | Local development with hot reload |
| Production | `docker-compose-prod.yml` | Production-ready with optimizations |
| Full Stack | `docker-compose.yml` | Complete infrastructure |

---

## 📁 Project Structure

```
StockVisionAI/
├── src/
│   ├── main/
│   │   ├── java/com/jartiste/stockvisionai/
│   │   │   ├── StockvisionaiApplication.java
│   │   │   ├── application/          # Application Layer
│   │   │   │   ├── mapper/          # MapStruct mappers
│   │   │   │   └── service/         # Business services
│   │   │   ├── domain/               # Domain Layer
│   │   │   │   ├── entity/          # Domain entities
│   │   │   │   ├── enums/           # Domain enums
│   │   │   │   ├── exception/       # Custom exceptions
│   │   │   │   └── repository/      # Repository interfaces
│   │   │   ├── infrastructure/       # Infrastructure Layer
│   │   │   │   ├── ai/              # AI integration
│   │   │   │   ├── config/          # Configuration
│   │   │   │   ├── filter/          # Security filters
│   │   │   │   ├── persistence/     # Persistence adapters
│   │   │   │   ├── security/        # Security components
│   │   │   │   ├── seeder/          # Database seeders
│   │   │   │   └── service/         # Infrastructure services
│   │   │   └── presentation/         # Presentation Layer
│   │   │       ├── advice/          # Global exception handlers
│   │   │       ├── controller/      # REST controllers
│   │   │       └── dto/             # DTOs (Request/Response)
│   │   └── resources/
│   │       ├── application.yaml      # Main configuration
│   │       ├── application-dev.yml   # Dev configuration
│   │       └── application-prod.yml  # Prod configuration
│   └── test/                         # Test classes
├── target/                           # Build output
├── docker-compose.yml                # Docker orchestration
├── docker-compose-dev.yml            # Dev environment
├── docker-compose-prod.yml           # Prod environment
├── Dockerfile                        # Multi-stage build
├── Jenkinsfile                       # CI/CD pipeline
├── pom.xml                           # Maven dependencies
├── init-vault.sh                     # Vault initialization (Linux/Mac)
├── init-vault.ps1                    # Vault initialization (Windows)
└── README.md                         # Project documentation
```

---

## 🧪 Testing

### Run Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AuthServiceImplTest

# Run with coverage report
mvn clean test jacoco:report
```

### Test Structure

```
src/test/java/com/jartiste/stockvisionai/
├── AuthServiceImplTest.java          # Authentication service tests
└── StockvisionaiApplicationTests.java # Application context tests
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

### Development Workflow

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Code Standards

- Follow Java naming conventions
- Write meaningful commit messages
- Add unit tests for new features
- Update documentation as needed
- Ensure code passes SonarCloud quality gates

### Commit Message Format

```
<type>(<scope>): <subject>

<body>

<footer>
```

Types: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`

Example:
```
feat(product): add bulk import functionality

Implement CSV import for products with validation
and error reporting.

Closes #123
```

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Authors

- ** Mohamed Moustir ** - [@mohamed-moustir](https://github.com/mohamed-moustir)
- **Younes Bousfiha** - [@younesbousfiha](https://github.com/younesbousfiha)

---

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- HashiCorp for Vault
- DeepSeek for AI capabilities
- The open-source community

---

## 📞 Support

For support, questions, or feedback:

- 📧 Email: contact@younesbousfiha.com
- 🐛 Issues: [GitHub Issues](https://github.com/younesbousfiha/StockVisionAI/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/younesbousfiha/StockVisionAI/discussions)


---

<div align="center">

**Made with ❤️ by Mohamed Moustir & Younes Bousfiha**

⭐ Star this repository if you find it helpful!

[Report Bug](https://github.com/younesbousfiha/StockVisionAI/issues) •
[Request Feature](https://github.com/younesbousfiha/StockVisionAI/issues)

</div>

