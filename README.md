# Pet API

REST API for retrieving pet information using a contract-first approach with OpenAPI.

The application exposes a server-side API contract and consumes an external Pet API through a generated client.

---

## Architecture

The application follows a layered architecture:
Controller
|
v
Service
|
v
External Client
|
v
External API

Responsibilities:

- **Controller**
    - Implements the OpenAPI generated server contract.
    - Handles HTTP requests and responses.

- **Service**
    - Contains business logic.
    - Orchestrates application flows.

- **External Client**
    - Encapsulates communication with external services.
    - Uses OpenAPI generated client code.

- **Mapper**
    - Converts generated API models into domain models.

- **Exception Handler**
    - Centralizes API error responses.

---


## Technologies

- Java 17
- Spring Boot 3.5
- OpenAPI Generator
- MapStruct
- Gradle

## Contract First Approach

OpenAPI contracts are the source of truth.

Server-side interfaces are generated from the API specification and implemented by controllers.

External API clients are also generated from OpenAPI specifications, avoiding manual REST client implementations.

---

## Running locally

Start the application:

```bash
./gradlew bootRun
```

The API will be available at:

http://localhost:8080

Swagger UI:

http://localhost:8080/swagger-ui/index.html


## Running with Docker

Build the image:
```bash
docker build -t pet-api .
```

## Run the container:
```bash
docker run -p 8080:8080 pet-api
```

The application will be available at:

http://localhost:8080


## Testing

Run unit tests:
```bash
./gradlew test
```


## Tests cover:

- Controller behavior
- Service logic
- External client error handling
- Exception scenarios

##  Decisions
- Contract-first approach for both server and client sides.
- Generated API models are not used as domain models.
- External API communication is isolated behind a dedicated client layer.
- MapStruct is used for model transformation.
- Centralized exception handling through RestControllerAdvice.
- External service failures are translated into application-specific exceptions.
- Validation rules are defined in the OpenAPI contract.


## Project structure
```text
src/main/java
|
├── controller
├── service
├── client
├── mapper
├── model
├── exception
└── config
```