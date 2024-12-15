# Airport Management API

This is the backend API for the Airport Management system, built with Spring Boot. It provides endpoints to manage flights, passengers, airports, gates, and aircraft.

## Features

- Manage flight details, including departure and arrival times.
- Manage passengers, including adding passengers to flights.
- Retrieve information about airports, gates, and aircraft.

## Technologies

- **Backend Framework:** Spring Boot
- **Database:** MySQL (Amazon RDS)
- **Serialization:** DTOs and `@JsonView` for dynamic response views
- **Deployment:** Dockerized and deployed on AWS EC2

## Setup

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd backend
   ```

2. Configure the database:

   - Update the `application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://<your-rds-endpoint>:3306/<database-name>
     spring.datasource.username=<your-username>
     spring.datasource.password=<your-password>
     ```

3. Build and run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the API at `http://localhost:8080`.

## API Endpoints

### Aircraft

- **GET /aircraft/all**  
  Retrieve all aircraft in a table-friendly format.

- **GET /aircraft/id/{id}**  
  Retrieve detailed information for a specific aircraft by its ID.

- **POST /aircraft**  
  Add a new aircraft.  
  **Consumes:** JSON payload.

- **PATCH /aircraft/id/{id}**  
  Update an existing aircraft by its ID.

- **DELETE /aircraft/id/{id}**  
  Delete an aircraft by its ID.

---

### Airline

- **GET /airline/all**  
  Retrieve all airlines in a table-friendly format.

- **GET /airline/id/{id}**  
  Retrieve detailed information for a specific airline by its ID.

- **GET /airline/name/{name}**  
  Retrieve an airline by its name.

- **POST /airline**  
  Add a new airline.  
  **Consumes:** JSON payload.

- **PATCH /airline/id/{id}**  
  Update an existing airline by its ID.

- **DELETE /airline/id/{id}**  
  Delete an airline by its ID.

---

### Airport

- **GET /airport/all**  
  Retrieve all airports in a table-friendly format.

- **GET /airport/id/{id}**  
  Retrieve detailed information for a specific airport by its ID.

- **GET /airport/{id}/gates**  
  Retrieve all gates associated with a specific airport by its ID.

- **POST /airport**  
  Add a new airport.  
  **Consumes:** JSON payload.

- **POST /airport/{id}/gates**  
  Add a new gate to a specific airport by its ID.

- **PATCH /airport/id/{id}**  
  Update an existing airport by its ID.

- **DELETE /airport/id/{id}**  
  Delete an airport by its ID.

---

### City

- **GET /city/all**  
  Retrieve all cities in a table-friendly format.

- **GET /city/id/{id}**  
  Retrieve detailed information for a specific city by its ID.

- **GET /city/name/{name}**  
  Retrieve a city by its name.

- **POST /city**  
  Add a new city.  
  **Consumes:** JSON payload.

- **PATCH /city/id/{id}**  
  Update an existing city by its ID.

- **DELETE /city/id/{id}**  
  Delete a city by its ID.

---

### Flight

- **GET /flight/all**  
  Retrieve all flights in a table-friendly format.

- **GET /flight/id/{id}**  
  Retrieve detailed information for a specific flight by its ID.

- **POST /flight**  
  Add a new flight.  
  **Consumes:** JSON payload.

- **POST /flight/id/{flightId}/passengers/add/{passengerId}**  
  Add a passenger to a specific flight by their IDs.

- **PATCH /flight/id/{id}**  
  Update an existing flight by its ID.

- **DELETE /flight/id/{id}**  
  Delete a flight by its ID.

---

### Passenger

- **GET /passenger/all**  
  Retrieve all passengers in a table-friendly format.

- **GET /passenger/id/{id}**  
  Retrieve detailed information for a specific passenger by their ID.

- **GET /passenger/name/{name}**  
  Retrieve a passenger by their name.

- **POST /passenger**  
  Add a new passenger.  
  **Consumes:** JSON payload.

- **PATCH /passenger/id/{id}**  
  Update an existing passenger by their ID.

- **DELETE /passenger/id/{id}**  
  Delete a passenger by their ID.

## Testing

Run unit tests with:

```bash
./mvnw test
```
