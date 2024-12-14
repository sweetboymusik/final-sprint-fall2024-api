package com.keyin.airline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirlineTableDTOTest {

    @Test
    void testAirlineTableDTOConstructorAndGetters() {
        AirlineTableDTO dto = new AirlineTableDTO(1, "Test Airline", "Test Country", 5);

        assertEquals(1, dto.getId());
        assertEquals("Test Airline", dto.getName());
        assertEquals("Test Country", dto.getCountry());
        assertEquals(5, dto.getAircraft());
    }

    @Test
    void testSetters() {
        AirlineTableDTO dto = new AirlineTableDTO(0, null, null, 0);

        dto.setId(2);
        dto.setName("Updated Airline");
        dto.setCountry("Updated Country");
        dto.setAircraft(10);

        assertEquals(2, dto.getId());
        assertEquals("Updated Airline", dto.getName());
        assertEquals("Updated Country", dto.getCountry());
        assertEquals(10, dto.getAircraft());
    }
}