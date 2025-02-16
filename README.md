# CQRS + Clean Architecture with Spring Boot and H2 Database embedded

This project is a simple example of how to implement a CQRS architecture with Spring Boot and H2 Database embedded.

## CQRS Structure

```txt
/src/main/java/com/rasysbox/ws
│── /application                # Application layer (CQRS - Commands & Queries)
│    ├── /commands              # Modify data commands (Create, Update, Delete)
│    ├── /queries               # Queries for obtaining data (Read)
│    ├── /dtos                  # DTOs for data transfer
│── /domain                     # Domain layer (Entities and Contracts)
│    ├── /models                # Domain models (UserDomain)
│    ├── /repositories          # Interfaces of Repositories (Abstraccions)
│── /infrastructure             # Infrastructure layer (Persistence and Adapters)
│    ├── /adapters              # Adapters (Repositories, Mappers, etc)
│    ├── /controllers           # Controllers (REST)
│    ├── /persistence           # Repositories implementations (JPA)
│    │    ├── /entities         # JPA entities (UserEntity)
│    │    ├── /repositories     # Repositories implementations (JPA)
│    ├── /config                # Configurations (H2, Security, etc)
│── /shared                     # Domain events (Event Sourcing)
│    ├── /mappers               # Mappers with MapStruct
│    ├── /exceptions            # Mappers with MapStruct
│    ├── /utils                 # Utility classes
│── AppWsApplication.java       # Initial class of the application
```

## Technologies

- Java 17+
- Spring Boot 3.3.8
- H2 Database

## How to run

1. Run the command `mvn clean install` to build the project.
2. Run the command `mvn spring-boot:run -e SPRING_PROFILES_ACTIVE=local` to start the application.
3. Access the URL `http://localhost:9095/swagger-ui/index.html` to see the API documentation.
4. Access the URL `http://localhost:9095/h2-console` to see the H2 Database console.
5. Use the following credentials to access the H2 Database console:
    - JDBC URL: `jdbc:h2:mem:clean-architecture`
    - User Name: `sa`
    - Password: ``

## API

### Create a new user

```curl
curl -X 'POST' \
  'http://localhost:9095/mapstruct/v1/users/create-user' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "John Doe",
  "email": "john.doe@email.com"
}'
```

```http
POST /mapstruct/v1/users/create-user
```

Request body:

```json
{
  "name": "John Doe",
  "email": "john.doe@email.com"
}
```

### Get all users

```curl
curl -X 'GET' \
  'http://localhost:9095/mapstruct/v1/users/find-all-users' \
  -H 'accept: */*'
```

```http
GET /mapstruct/v1/users/find-all-users
```

### Get user by ID

```curl
curl -X 'GET' \
  'http://localhost:9095/mapstruct/v1/users/find-user-by-id/1' \
  -H 'accept: */*'
```

```http
GET /mapstruct/v1/users/find-user-by-id/1
```

## Author

- **Raul Bolivar Navas** - [rasysbox](https://github.com/raulrobinson/cqrs-clean-architecture-mapstruct-jpa)

## License

This project is licensed under the MIT License - see the [LICENSE](https://www.apache.org/licenses/LICENSE-2.0) file for details.
```