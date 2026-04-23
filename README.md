# Smart Campus API (CSA Coursework)

Name: Kavidu Wijenayaka
UOW ID: w2119864
IIT ID: 20240287

## Overview

Hi! I am Kavidu. This is my project for the Client-Server Architectures coursework.
It is a RESTful API used to manage rooms and sensors in a smart campus system.

The API supports:

Room management
Sensor registration
Sensor readings tracking

This project is built using Java, JAX-RS (Jersey), Maven, and Apache Tomcat.
All data is stored in memory, so it is fast, but data resets when the server restarts.

---

## Technologies Used

 Java 17
 JAX-RS (Jersey)
 Apache Tomcat
 Maven (WAR packaging)
 Postman

---

## Project Structure

CSA-CW/
├── pom.xml
├── README.md
├── src/
│   └── main/
│       ├── java/com/csa/smartcampus/
│       │   ├── config/
│       │   ├── model/
│       │   ├── resource/
│       │   ├── store/
│       │   ├── exception/
│       │   └── filter/
│       └── webapp/
│           └── WEB-INF/
│               └── web.xml

## How to Run the Project

🔹 Steps

1. Clone the repository: git clone <https://github.com/kavidu284/CSA-CW.git>

2. Open project in **NetBeans**
3. Configure **Apache Tomcat** in:

   Tools → Servers

4. Set Tomcat in project:

   Right Click Project → Properties → Run → Server = Tomcat

5. Run project

---

## Base URL

<http://localhost:8080/CSA-CW/api/v1/>

---

## API Endpoints

### Rooms

* `POST /rooms`
* `GET /rooms`
* `GET /rooms/{id}`
* `DELETE /rooms/{id}`

### Sensors

* `POST /sensors`
* `GET /sensors`
* `GET /sensors?type=Temperature`
* `GET /sensors/{id}`

### Sensor Readings

* `GET /sensors/{id}/readings`
* `POST /sensors/{id}/readings`

---

## Example CURL Commands

### 1. Create Room

```bash
curl -X POST http://localhost:8080/CSA-CW/api/v1/rooms \
-H "Content-Type: application/json" \
-d "{\"id\":\"LAB-101\",\"name\":\"Computer Lab\",\"capacity\":50}"
```

### 2. Get Rooms

```bash
curl http://localhost:8080/CSA-CW/api/v1/rooms
```

### 3. Create Sensor

```bash
curl -X POST http://localhost:8080/CSA-CW/api/v1/sensors \
-H "Content-Type: application/json" \
-d "{\"id\":\"TEMP-001\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":25,\"roomId\":\"LAB-101\"}"
```

### 4. Add Reading

```bash
curl -X POST http://localhost:8080/CSA-CW/api/v1/sensors/TEMP-001/readings \
-H "Content-Type: application/json" \
-d "{\"value\":28.5}"
```

### 5. Get Sensor Readings

```bash
curl http://localhost:8080/CSA-CW/api/v1/sensors/TEMP-001/readings
```

---

## Error Handling

The API returns appropriate HTTP status codes:

| Code | Description          |
| ---- | -------------------- |
| 400  | Bad Request          |
| 403  | Forbidden            |
| 404  | Not Found            |
| 409  | Conflict             |
| 422  | Unprocessable Entity |

Example error:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found",
  "timestamp": 123456789
}
```

---

## Nested Resource Design

Sensor readings are implemented as:

/sensors/{id}/readings

This represents a one-to-many relationship between sensors and readings.

---

## 📊 Logging

The API includes a logging filter that prints:

* Incoming requests
* Outgoing responses

Example:

Incoming Request -> Method: GET, URI: /api/v1/rooms
Outgoing Response -> Status: 200

---

## 🧪 Testing

Tested using Postman:

* CRUD operations
* Filtering
* Nested resources
* Error cases (404, 409, 422, 403)

---

### Architecture

Layered architecture with separation of concerns:

* Resource layer
* Model layer
* Store layer
* Exception handling
* Logging filter

### Versioning

URI-based versioning:

/api/v1/

### Data Storage

In-memory storage using `HashMap`

---

## 🎯 Conclusion

This project successfully demonstrates:

* RESTful API design
* Proper HTTP methods and status codes
* Nested resources
* Error handling
* Logging

The application is deployed using Apache Tomcat and is fully functional.

---

## Coursework Answers

### 1. JAX-RS Resource Lifecycle

JAX-RS creates a new instance of a resource class for each HTTP request. This ensures thread safety because no shared state is kept between requests.

---

### 2. What is HATEOAS

HATEOAS (Hypermedia as the Engine of Application State) is a REST principle where the server provides links in responses so clients can dynamically navigate available actions.

---

### 3. Why use roomId instead of embedding full Room object

Using `roomId` avoids duplication, reduces response size, and keeps resources independent. It also prevents inconsistencies when room data changes.

---

### 4. Why DELETE is idempotent

DELETE is idempotent because calling it multiple times has the same result. After the resource is deleted, further DELETE requests do not change the system state.

---

### 5. What happens if Content-Type is not JSON

If the request body is not JSON, the server returns **415 Unsupported Media Type**, because the API expects `application/json`.

---

### 6. Why use @QueryParam instead of multiple endpoints

`@QueryParam` allows flexible filtering using URL parameters (e.g., `/sensors?type=Temperature`) without creating separate endpoints, making the API cleaner and more scalable.

---

### 7. Advantage of sub-resource locator

Sub-resource locators allow hierarchical design by delegating handling of nested resources. This improves modularity and code organization.

---

### 8. Why use 422 instead of 404

422 is used when the request is valid but contains incorrect data (e.g., invalid roomId).
404 is used when a resource does not exist.

---

### 9. Risk of returning stack trace in API

Returning stack traces exposes internal system details, which can create security vulnerabilities. Instead, user-friendly error messages should be returned.

---

### 10. Why use filters for logging

Filters provide a centralized way to handle logging for all requests and responses, avoiding repetition in resource classes and improving maintainability.

---

---
