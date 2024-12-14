package com.keyin.aircraft;

import com.keyin.airline.Airline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftTest {

    @Test
    void testAircraftDefaultConstructorAndSetters() {
        Aircraft aircraft = new Aircraft();
        Airline airline = new Airline("Test Airline", "Test Country");

        aircraft.setId(1);
        aircraft.setType("Boeing 747");
        aircraft.setPassengerCapacity(400);
        aircraft.setAirline(airline);

        assertEquals(1, aircraft.getId());
        assertEquals("Boeing 747", aircraft.getType());
        assertEquals(400, aircraft.getPassengerCapacity());
        assertEquals(airline, aircraft.getAirline());
    }

    @Test
    void testAircraftParameterizedConstructor() {
        Airline airline = new Airline("Test Airline", "Test Country");

        Aircraft aircraft = new Aircraft("Airbus A320", 180, airline);

        assertEquals("Airbus A320", aircraft.getType());
        assertEquals(180, aircraft.getPassengerCapacity());
        assertEquals(airline, aircraft.getAirline());
    }

    @Test
    void testAircraftDTOConstructor() {
        Airline airline = new Airline("Test Airline", "Test Country");
        AircraftDTO aircraftDTO = new AircraftDTO("Boeing 737", 150, 1); // Airline ID passed in DTO

        Aircraft aircraft = new Aircraft(aircraftDTO, airline);

        assertEquals("Boeing 737", aircraft.getType());
        assertEquals(150, aircraft.getPassengerCapacity());
        assertEquals(airline, aircraft.getAirline());
    }
}