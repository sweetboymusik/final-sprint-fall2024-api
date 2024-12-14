package com.keyin.aircraft;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftDTOTest {

    @Test
    void testAircraftDTOConstructorAndGetters() {
        String type = "Boeing 747";
        int passengerCapacity = 400;
        int airlineId = 1;

        AircraftDTO aircraftDTO = new AircraftDTO(type, passengerCapacity, airlineId);

        assertEquals("Boeing 747", aircraftDTO.getType());
        assertEquals(400, aircraftDTO.getPassengerCapacity());
        assertEquals(1, aircraftDTO.getAirlineId());
    }

    @Test
    void testAircraftDTOSetters() {
        AircraftDTO aircraftDTO = new AircraftDTO("", 0, 0);

        aircraftDTO.setType("Airbus A320");
        aircraftDTO.setPassengerCapacity(180);
        aircraftDTO.setAirlineId(2);

        assertEquals("Airbus A320", aircraftDTO.getType());
        assertEquals(180, aircraftDTO.getPassengerCapacity());
        assertEquals(2, aircraftDTO.getAirlineId());
    }
}