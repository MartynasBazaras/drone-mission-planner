# 🚁 Drone Mission Planner API

REST API for managing drone missions and waypoints.

This project is built with Java, Spring Boot and PostgreSQL and is intended as the first step towards a complete UAV mission planning system.

---

## Features

- Create missions
- View all missions
- Create waypoints
- View mission waypoints
- PostgreSQL database
- Spring Data JPA
- REST API
- Postman tested

---

## Technologies

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Postman

---

## Project Structure

```
Controller
    ↓
Service
    ↓
Repository
    ↓
Database (PostgreSQL)
```

---

## API Endpoints

### Missions

| Method | Endpoint |
|--------|----------|
| GET | /missions |
| POST | /missions |

### Waypoints

| Method | Endpoint |
|--------|----------|
| GET | /missions/{missionId}/waypoints |
| POST | /missions/{missionId}/waypoints |

---

## Example Request

### Create Mission

```json
{
    "name": "Forest Inspection",
    "description": "Inspect damaged trees after a storm"
}
```

---

## Future Improvements

- Delete missions
- Update missions
- DTO layer
- Validation
- Global Exception Handler
- Swagger/OpenAPI
- Docker
- Authentication
- PX4 integration
- Gazebo simulator
- Python UAV controller
- ROS 2 support

---

## Author

Martynas Bazaras