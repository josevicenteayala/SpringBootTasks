# CRUD

## Overview
Crud is a simple Spring Boot application that allows you to perform CRUD (Create, Read, Update, Delete) operations on products. It provides RESTful APIs to manage products and stores them in a database.
For seamless interaction with the product data, it's imperative to transmit a Bearer token that encapsulates pertinent user details as the subject. To illustrate, an example of a decoded JWT token appears as follows:
* header

```
{
  "alg": "HS256"
}
```
* payload
```
{
    "roles": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "iss": "crud-security",
    "sub": "user",
    "iat": 1691796947,
    "exp": 1692660947
}
```

* signature

```
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  
your-256-bit-secret

) secret base64 encoded
```

## Features
The following features require authentication token
- Add a new product with a name, price, and description.
- Retrieve a product by its ID.
- Update an existing product's name, price, or description.
- Delete a product by its ID.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA (for database interactions)
- H2 Database (in-memory database for testing and development)
- Spring Web (for creating RESTful APIs)
- Spring Security
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
curl --location 'localhost:8080/products/' --header 'Content-Type: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpc3MiOiJjcnVkLXNlY3VyaXR5Iiwic3ViIjoidXNlciIsImlhdCI6MTY5MTc5Njk0NywiZXhwIjoxNjkyNjYwOTQ3fQ.0FalFdIEyLHwmJpYX1j_NQj5zcpVHb5NYwRtTixrtu4' --header 'Cookie: JSESSIONID=B6495F38E0CC3C823CCB6D0CE4A255D6' --data '{
    "login":"user1",
    "name":"name1",
    "price":1,
    "description":"product1"
}'
```

#### Retrieve all products:

``` bash
curl --location --request GET 'localhost:8080/products' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpc3MiOiJjcnVkLXNlY3VyaXR5Iiwic3ViIjoidXNlciIsImlhdCI6MTY5MTc5Njk0NywiZXhwIjoxNjkyNjYwOTQ3fQ.0FalFdIEyLHwmJpYX1j_NQj5zcpVHb5NYwRtTixrtu4' \
--header 'Cookie: JSESSIONID=6F2DEFD80C9BFC6B37F4780E939DCC3B' \
--data '{
    "login":"user2",
    "name":"name2",
    "price":2,
    "description":"product2"
}'
```

Response
```cypher
[
    {
        "id": 1,
        "name": "name1",
        "price": 1.0,
        "description": "product1"
    },
    {
        "id": 2,
        "name": "name2",
        "price": 2.0,
        "description": "product2"
    }
]
```

#### Retrieve a product by ID:

``` bash
curl --location --request GET 'localhost:8080/products/2' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpc3MiOiJjcnVkLXNlY3VyaXR5Iiwic3ViIjoidXNlciIsImlhdCI6MTY5MTc5Njk0NywiZXhwIjoxNjkyNjYwOTQ3fQ.0FalFdIEyLHwmJpYX1j_NQj5zcpVHb5NYwRtTixrtu4' \
--header 'Cookie: JSESSIONID=B6495F38E0CC3C823CCB6D0CE4A255D6' \
--data '{
    "login":"user1",
    "name":"name1",
    "price":1,
    "description":"product1"
}'
```

#### Update an existing product:
```bash
curl --location --request PUT 'localhost:8080/products/2' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpc3MiOiJjcnVkLXNlY3VyaXR5Iiwic3ViIjoidXNlciIsImlhdCI6MTY5MTc5Njk0NywiZXhwIjoxNjkyNjYwOTQ3fQ.0FalFdIEyLHwmJpYX1j_NQj5zcpVHb5NYwRtTixrtu4' \
--header 'Cookie: JSESSIONID=B6495F38E0CC3C823CCB6D0CE4A255D6' \
--data '{
    "login":"user2",
    "name":"name2",
    "price":2,
    "description":"product2"
}'
```

#### Delete a product by ID:
```bash
curl --location --request DELETE 'localhost:8080/products/1' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpc3MiOiJjcnVkLXNlY3VyaXR5Iiwic3ViIjoidXNlciIsImlhdCI6MTY5MTc5Njk0NywiZXhwIjoxNjkyNjYwOTQ3fQ.0FalFdIEyLHwmJpYX1j_NQj5zcpVHb5NYwRtTixrtu4' \
--header 'Cookie: JSESSIONID=6F2DEFD80C9BFC6B37F4780E939DCC3B' \
--data '{
    "login":"user2",
    "name":"name2",
    "price":2,
    "description":"product2"
}'
```

###  Testing
The application includes unit tests and integration tests to ensure its correctness. You can run the tests using Gradle:

```bash
./gradlew test
```
### Contributing
If you find any issues with the application or have suggestions for improvement, please feel free to open an issue or submit a pull request.

