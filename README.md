# E-Commerce Microservices Project

## Overview

This project demonstrates an e-commerce application built using microservices architecture. The system comprises four microservices: `Product Service`, `User Service`, `Order Service`, and `Payment Service`. Each microservice is a standalone Spring Boot application with its own database.

## Microservices

1. **Product Service**
    - Manages the product catalog and product details.
2. **User Service**
    - Handles user registration, authentication, and profile management.
3. **Order Service**
    - Manages order placement, status, and history.
4. **Payment Service**
    - Handles payment processing and status updates.

## Architecture

Each microservice is implemented using Spring Boot and communicates with other services using REST APIs. MySQL is used as the database for each microservice.

## Project Structure

```
microservices
├── product-service
│   ├── src
│   ├── pom.xml
├── user-service
│   ├── src
│   ├── pom.xml
├── order-service
│   ├── src
│   ├── pom.xml
├── payment-service
│   ├── src
│   ├── pom.xml
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- MySQL

### Setting Up Databases

Create separate databases for each microservice:

```sql
CREATE DATABASE product_db;
CREATE DATABASE user_db;
CREATE DATABASE order_db;
CREATE DATABASE payment_db;
```

### Configuration

Update the database configurations in the `application.properties` file of each service to match your MySQL setup.

### Building and Running

Navigate to each microservice directory and run the following commands:

```bash
mvn clean install
mvn spring-boot:run
```

Ensure you run each service in a different terminal or use your IDE's run configurations to start them simultaneously.

### Service Ports

- **Product Service**: `http://localhost:8081`
- **User Service**: `http://localhost:8083`
- **Order Service**: `http://localhost:8082`
- **Payment Service**: `http://localhost:8084`

## API Endpoints

### Product Service

- **GET** `/api/products` - Get all products
- **GET** `/api/products/{id}` - Get product by ID
- **POST** `/api/products` - Create a new product
- **PUT** `/api/products/{id}` - Update an existing product
- **DELETE** `/api/products/{id}` - Delete a product

### User Service

- **GET** `/api/users` - Get all users
- **GET** `/api/users/{id}` - Get user by ID
- **POST** `/api/users` - Create a new user
- **PUT** `/api/users/{id}` - Update an existing user
- **DELETE** `/api/users/{id}` - Delete a user

### Order Service

- **GET** `/api/orders` - Get all orders
- **GET** `/api/orders/{id}` - Get order by ID
- **POST** `/api/orders` - Create a new order
- **PUT** `/api/orders/{id}` - Update an existing order
- **DELETE** `/api/orders/{id}` - Delete an order

### Payment Service

- **GET** `/api/payments` - Get all payments
- **GET** `/api/payments/{id}` - Get payment by ID
- **POST** `/api/payments` - Create a new payment
- **PUT** `/api/payments/{id}` - Update an existing payment
- **DELETE** `/api/payments/{id}` - Delete a payment

## Testing Inter-Service Communication

1. **Start all services**:
    - `Product Service` on port `8081`
    - `User Service` on port `8083`
    - `Order Service` on port `8082`
    - `Payment Service` on port `8084`

2. **Create a user** in `User Service`:
   ```bash
   POST http://localhost:8083/api/users
   {
       "username": "user1",
       "password": "password1",
       "email": "user1@example.com"
   }
   ```

3. **Create a product** in `Product Service`:
   ```bash
   POST http://localhost:8081/api/products
   {
       "name": "Product 1",
       "description": "Description 1",
       "price": 10.00
   }
   ```

4. **Create an order** in `Order Service`:
   ```bash
   POST http://localhost:8082/api/orders
   {
       "userId": 1,
       "productId": 1,
       "quantity": 10
   }
   ```

5. **Create a payment** in `Payment Service`:
   ```bash
   POST http://localhost:8084/api/payments
   {
       "userId": 1,
       "orderId": 1,
       "amount": 100.00,
       "status": "COMPLETED"
   }
   ```

## Additional Features

- **Service Discovery**: Consider using Eureka or Consul for service discovery.
- **API Gateway**: Use Spring Cloud Gateway or Zuul for API Gateway.
- **Distributed Tracing**: Use Zipkin or Jaeger for tracing requests across microservices.
- **Centralized Configuration**: Use Spring Cloud Config for centralized configuration management.

## License

This project is licensed under the MIT License.

