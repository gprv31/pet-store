# Pet API

## Architecture

Controller
-> Service
-> External Client

## Technologies

- Java 17
- Spring Boot 3.5
- OpenAPI Generator
- MapStruct
- Gradle

## Run

./gradlew bootRun

## Testing

./gradlew test

## Decisions

- Contract first approach (Sever side and clietn side)
- External API isolated behind client layer
- Domain models separated from generated models