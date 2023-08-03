# CRUD

## Overview
Crud is a simple Spring Boot application that allows you to perform CRUD (Create, Read, Update, Delete) operations on products. It provides RESTful APIs to manage products and stores them in a database.

## Features
- Add a new product with a name, price, and description.
- Retrieve a product by its ID.
- Update an existing product's name, price, or description.
- Delete a product by its ID.

## Technologies Used
- Java 11
- Spring Boot
- Spring Data JPA (for database interactions)
- H2 Database (in-memory database for testing and development)
- Spring Web (for creating RESTful APIs)
- Maven (for project management and build)

## Prerequisites
- Java Development Kit (JDK) 8 or later
- Gradle

## Getting Started
Follow these steps to run the MyCommandLineRunner application:

1. Clone the repository or download the source code.

```
git clone https://github.com/josevicenteayala/SpringBootTasks.git
```

2. Build the project using Gradle:

```
./gradlew build
```

3. Run the application using Gradle:
```
./gradlew bootRun
```


4. The application will start, and you should see the following message in the console:

The application should now be up and running at http://localhost:8080.

## API Endpoints
The following endpoints are available for managing products:

- GET /products: Retrieve all products.
- GET /products/{id}: Retrieve a product by its ID.
- POST /products: Create a new product. Request body should contain product details (name, price, description).
- PUT /products/{id}: Update an existing product. Request body should contain product details (name, price, description).
- DELETE /products/{id}: Delete a product by its ID.

### Examples
#### Create a new product:

``` bash
curl -X POST -H "Content-Type: application/json" -d '{"name":"New Product","price":19.99,"description":"New Product Description"}' http://localhost:8080/products
```

#### Retrieve a product by ID:

``` bash
curl http://localhost:8080/products/1
```

#### Update an existing product:
```bash
curl -X PUT -H "Content-Type: application/json" -d '{"name":"Updated Product","price":24.99,"description":"Updated Product Description"}' http://localhost:8080/products/1
```

#### Delete a product by ID:
```bash
curl -X DELETE http://localhost:8080/products/1
```

###  Testing
The application includes unit tests and integration tests to ensure its correctness. You can run the tests using Maven:

```bash
./gradlew test
```
### Contributing
If you find any issues with the application or have suggestions for improvement, please feel free to open an issue or submit a pull request.

