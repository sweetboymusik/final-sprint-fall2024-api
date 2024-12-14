package com.keyin.aircraft;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftFormattedDTOTest {

    @Test
    void testConstructorWithIdAndType() {
        AircraftFormattedDTO dto = new AircraftFormattedDTO(1, "Boeing 737");
        assertEquals("Boeing 737 (ID: 1)", dto.getNameFormatted());
    }

    @Test
    void testConstructorWithIdTypeAndAirline() {
        AircraftFormattedDTO dto = new AircraftFormattedDTO(2, "Airbus A320", "Air Canada");
        assertEquals("Airbus A320 (ID: 2) | Air Canada", dto.getNameFormatted());
    }

    @Test
    void testSetNameFormatted() {
        AircraftFormattedDTO dto = new AircraftFormattedDTO(1, "Boeing 737");
        dto.setNameFormatted("Updated Format");
        assertEquals("Updated Format", dto.getNameFormatted());
    }
}