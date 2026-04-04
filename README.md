# Journal App - Spring Boot REST API

A backend REST API for managing personal journal entries with user authentication and role based authorization.

## Tech Stack
- Java 21
- Spring Boot 3.3.5
- MongoDB Atlas
- Spring Security
- Lombok
- Maven

## Features
- Create and manage journal entries
- User authentication with BCrypt password encoding
- Role based authorization (ADMIN / USER)
- Each user can only access their own journal entries
- Admin can view all users
- Stateless REST API

## API Endpoints

### Public
| Method | URL | Description |
|--------|-----|-------------|
| POST | /public/create-user | Register new user |
| GET | /public/health-check | Health check |

### Journal (Authenticated)
| Method | URL | Description |
|--------|-----|-------------|
| GET | /journal | Get all entries |
| POST | /journal | Create new entry |
| GET | /journal/id/{id} | Get entry by ID |
| PUT | /journal/id/{id} | Update entry |
| DELETE | /journal/id/{id} | Delete entry |

### User (Authenticated)
| Method | URL | Description |
|--------|-----|-------------|
| PUT | /user | Update user |
| DELETE | /user | Delete user |

### Admin
| Method | URL | Description |
|--------|-----|-------------|
| GET | /admin/all-users | Get all users |

## Project Structure
src/main/java/com/projectOne/journalApp/
├── config/
│   ├── SpringSecurity.java
│   └── UserDetailsServiceImpl.java
├── controller/
│   ├── AdminController.java
│   ├── JournalEntryControllerV2.java
│   ├── PublicController.java
│   └── UserController.java
├── entity/
│   ├── JournalEntry.java
│   └── User.java
├── repository/
│   ├── JournalEntryRepository.java
│   └── UserRepository.java
├── service/
│   ├── JournalEntryService.java
│   └── UserService.java
└── JournalAppApplication.java 
  
## Security
- BCrypt password encoding
- HTTP Basic Authentication
- Stateless session management
- Role based access control

## How to Run
```bash
git clone https://github.com/Venkata-Chalapathi/journalapp-springboot.git
cd journalapp-springboot
mvn spring-boot:run
```

## Coming Soon
- JWT authentication
- Swagger documentation
