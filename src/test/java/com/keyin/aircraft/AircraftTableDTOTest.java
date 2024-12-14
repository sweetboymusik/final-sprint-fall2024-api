package com.keyin.aircraft;

import com.keyin.airline.AirlineFormattedDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftTableDTOTest {

    @Test
    void testAircraftTableDTOConstructorAndGetters() {
        // Given
        AirlineFormattedDTO airline = new AirlineFormattedDTO("Test Airline");

        // When
        AircraftTableDTO aircraftTableDTO = new AircraftTableDTO(1, "Boeing 737", 150, airline);

        // Then
        assertEquals(1, aircraftTableDTO.getId());
        assertEquals("Boeing 737", aircraftTableDTO.getType());
        assertEquals(150, aircraftTableDTO.getPassengerCapacity());
        assertEquals(airline, aircraftTableDTO.getAirline());
    }

    @Test
    void testAircraftTableDTOSetters() {
        // Given
        AircraftTableDTO aircraftTableDTO = new AircraftTableDTO(0, null, 0, null);
        AirlineFormattedDTO airline = new AirlineFormattedDTO("Updated Airline");

        // When
        aircraftTableDTO.setId(2);
        aircraftTableDTO.setType("Airbus A320");
        aircraftTableDTO.setPassengerCapacity(180);
        aircraftTableDTO.setAirline(airline);

        // Then
        assertEquals(2, aircraftTableDTO.getId());
        assertEquals("Airbus A320", aircraftTableDTO.getType());
        assertEquals(180, aircraftTableDTO.getPassengerCapacity());
        assertEquals(airline, aircraftTableDTO.getAirline());
    }
}