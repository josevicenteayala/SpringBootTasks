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

### Actuator Integration

We've integrated Spring Boot Actuator to provide enhanced monitoring and management capabilities for our application. Actuator exposes various endpoints that offer insights into different aspects of the application's health and performance.

To access Actuator endpoints, navigate to `http://localhost:8080/manage`. Here are some of the key endpoints available:

- `/health`: Check the health status of the application.
- `/info`: Get information about the application.
- `/metrics`: Access various application metrics.

You can customize which endpoints are exposed and their base path by configuring the `application.properties` or `application.yml` file.

#### Examples

* GET: http://localhost:8080/manage
* Response:
```cypher
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/manage",
            "templated": false
        },
        "health": {
            "href": "http://localhost:8080/manage/health",
            "templated": false
        },
        "health-path": {
            "href": "http://localhost:8080/manage/health/{*path}",
            "templated": true
        },
        "info": {
            "href": "http://localhost:8080/manage/info",
            "templated": false
        },
        "metrics-requiredMetricName": {
            "href": "http://localhost:8080/manage/metrics/{requiredMetricName}",
            "templated": true
        },
        "metrics": {
            "href": "http://localhost:8080/manage/metrics",
            "templated": false
        }
    }
}
```
* GET http://localhost:8080/manage/health
* Response:
```cypher
{
    "status": "UP"
}
```

* GET http://localhost:8080/manage/metrics
* Response:
```cypher
{
    "names": [
        "application.ready.time",
        "application.started.time",
        "disk.free",
        "disk.total",
        "executor.active",
        "executor.completed",
        "executor.pool.core",
        "executor.pool.max",
        "executor.pool.size",
        "executor.queue.remaining",
        "executor.queued",
        "hikaricp.connections",
        "hikaricp.connections.acquire",
        "hikaricp.connections.active",
        "hikaricp.connections.creation",
        "hikaricp.connections.idle",
        "hikaricp.connections.max",
        "hikaricp.connections.min",
        "hikaricp.connections.pending",
        "hikaricp.connections.timeout",
        "hikaricp.connections.usage",
        "http.server.requests",
        "http.server.requests.active",
        "jdbc.connections.active",
        "jdbc.connections.idle",
        "jdbc.connections.max",
        "jdbc.connections.min",
        "jvm.buffer.count",
        "jvm.buffer.memory.used",
        "jvm.buffer.total.capacity",
        "jvm.classes.loaded",
        "jvm.classes.unloaded",
        "jvm.compilation.time",
        "jvm.gc.live.data.size",
        "jvm.gc.max.data.size",
        "jvm.gc.memory.allocated",
        "jvm.gc.memory.promoted",
        "jvm.gc.overhead",
        "jvm.gc.pause",
        "jvm.info",
        "jvm.memory.committed",
        "jvm.memory.max",
        "jvm.memory.usage.after.gc",
        "jvm.memory.used",
        "jvm.threads.daemon",
        "jvm.threads.live",
        "jvm.threads.peak",
        "jvm.threads.started",
        "jvm.threads.states",
        "logback.events",
        "process.cpu.usage",
        "process.files.max",
        "process.files.open",
        "process.start.time",
        "process.uptime",
        "spring.security.authorizations",
        "spring.security.authorizations.active",
        "spring.security.filterchains",
        "spring.security.filterchains.JwtAuthenticationFilter.after",
        "spring.security.filterchains.JwtAuthenticationFilter.before",
        "spring.security.filterchains.access.exceptions.after",
        "spring.security.filterchains.access.exceptions.before",
        "spring.security.filterchains.active",
        "spring.security.filterchains.authentication.anonymous.after",
        "spring.security.filterchains.authentication.anonymous.before",
        "spring.security.filterchains.authorization.after",
        "spring.security.filterchains.authorization.before",
        "spring.security.filterchains.context.async.after",
        "spring.security.filterchains.context.async.before",
        "spring.security.filterchains.context.holder.after",
        "spring.security.filterchains.context.holder.before",
        "spring.security.filterchains.context.servlet.after",
        "spring.security.filterchains.context.servlet.before",
        "spring.security.filterchains.header.after",
        "spring.security.filterchains.header.before",
        "spring.security.filterchains.logout.after",
        "spring.security.filterchains.logout.before",
        "spring.security.filterchains.requestcache.after",
        "spring.security.filterchains.requestcache.before",
        "spring.security.filterchains.session.url-encoding.after",
        "spring.security.filterchains.session.url-encoding.before",
        "spring.security.http.secured.requests",
        "spring.security.http.secured.requests.active",
        "system.cpu.count",
        "system.cpu.usage",
        "system.load.average.1m",
        "tomcat.sessions.active.current",
        "tomcat.sessions.active.max",
        "tomcat.sessions.alive.max",
        "tomcat.sessions.created",
        "tomcat.sessions.expired",
        "tomcat.sessions.rejected"
    ]
}
```

### Custom Health Indicators

We've implemented custom health indicators that go beyond the default health checks provided by Actuator. These indicators, located in the `com.task.crud.observability` package, allow us to monitor specific health aspects of our application. You can find more details in the `CustomHealthIndicator` class.


###  Testing
The application includes unit tests and integration tests to ensure its correctness. You can run the tests using Gradle:

```bash
./gradlew test
```
### Contributing
If you find any issues with the application or have suggestions for improvement, please feel free to open an issue or submit a pull request.

