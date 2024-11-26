# Product Catalog Cache

## Overview
This project is a product catalog service that uses caching to improve performance. It is built with Java, Spring Boot, and uses an H2 in-memory database.

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Spring Cache (Caffeine)
- H2 Database
- Maven
- Lombok
- MapStruct
- JUnit5, Mockito
- Flyway
- Swagger

## Getting Started

### Prerequisites
- JDK 17 or higher
- Maven 3.6.0 or higher

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/PashaFentisov/ProductCatalogCache.git
    cd product-catalog-cache
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

### Configuration
The application is configured using `application.yaml` located in `src/main/resources`. Key configurations include:

- **Database**: H2 in-memory database
- **Caching**: Caffeine cache with a maximum size of 1000 and expiration after 10 minutes
- **JPA**: Hibernate with `validate` DDL-auto setting

### Accessing H2 Console
The H2 console can be accessed at `http://localhost:8080/h2-console` with the following credentials:
- **URL**: `jdbc:h2:mem:product-catalog`
- **Username**: `sa`
- **Password**: (leave blank)

## Usage

### API Endpoints
- **Get Products**: `GET /products`
- **Get Product by ID**: `GET /products/{id}`
- **Create Product**: `POST /products`
- **Update Product**: `PUT /products/{id}`
- **Delete Product**: `DELETE /products/{id}`
- **Get Products by Category**: `GET /products/category/{category}`

### Logging
Logging is configured using Logback. Logs are written to the console and to daily rolling log files located in the `logs` directory.

### Security
Basic initial security is implemented using Spring Security. There are two default users:  
Admin role credentials:  
 - Username: admin
 - Password: admin_password

User role credentials:  
 - Username: user
 - Password: user_password
### Cache Metrics
Cache analytics is implemented using Actuator and can be accessed at:
 - http://localhost:8080/actuator/metrics/cache.gets
 - http://localhost:8080/actuator/metrics/cache.gets?tag=result:hit

### Swagger
 - http://localhost:8080/swagger-ui/index.html

### Docker
To build and run the Docker container for this application, follow these steps:

1. **Build the Docker image**:
    ```sh
    docker build -t product-catalog-cache .
    ```

2. **Run the Docker container**:
    ```sh
    docker run -d -p 8080:8080 product-catalog-cache
    ```

3. **Access the application**:
    Open your web browser and navigate to `http://localhost:8080` to access the application.
