# Library Management System API

## Project Description
The Library Management System API allows librarians to manage books, patrons, and borrowing records. It is built using Spring Boot and provides RESTful endpoints for various operations related to book and patron management.

## Features
- Book Management:
    - Retrieve a list of all books (GET /api/books)
    - Retrieve details of a specific book by ID (GET /api/books/{id})
    - Add a new book to the library (POST /api/books)
    - Update an existing book's information (PUT /api/books/{id})
      - Remove a book from the library (DELETE /api/books/{id})

- Patron Management:
    - Retrieve a list of all patrons (GET /api/patrons)
    - Retrieve details of a specific patron by ID (GET /api/patrons/{id})
    - Add a new patron to the system (POST /api/patrons)
    - Update an existing patron's information (PUT /api/patrons/{id})
    - Remove a patron from the system (DELETE /api/patrons/{id})

- Borrowing Management:
    - Allow a patron to borrow a book (POST /api/borrow/{bookId}/patron/{patronId})
    - Record the return of a borrowed book by a patron (PUT /api/return/{bookId}/patron/{patronId})

- Data Storage:
    - Uses a relational database (PostgreSQL) to persist book, patron, and borrowing record details
    - Proper relationships between entities (e.g., one-to-many between books and borrowing records)

- Validation and Error Handling:
    - Input validation for API requests
    - Graceful exception handling with appropriate HTTP status codes and error messages

- Security:
    - Basic authentication or JWT-based authorization to protect the API endpoints

- Logging:
    - Aspect-Oriented Programming (AOP) for logging method calls, exceptions, and performance metrics

- Caching:
    - Spring's caching mechanisms to cache frequently accessed data

- Transaction Management:
    - Declarative transaction management using Spring's `@Transactional` annotation

## Postman Testing
- Provided a postman .json file (Maids.cc-task.postman_collection.json), import it into postman to access and test the application endpoints.

## Technology Stack
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Spring Security
- Spring AOP
- Spring Cache

## Setup Instructions

### Prerequisites
- Java 11 or higher
- Maven
- A database (PostgreSQL)

### Running the Application

1. **Clone the repository:**
   ```sh
   git clone https://github.com/AhmedElbialy148/Library_Spring-Boot.git
   cd Library_Spring-Boot

2. **Configure the database:**
 Update the application.yml file with your database configurations. For example:
   ```sh
   Spring:
    datasource:
    url: jdbc:postgresql://(host):(port)/(DB name)
    username: (username)
    password: (password)
    driver-class-name: org.postgresql.Driver
    
    jpa:
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
    ddl-auto: update
    show-sql: false
    properties:
    hibernate:
    format_sql: true



