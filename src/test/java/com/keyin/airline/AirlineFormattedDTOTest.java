package com.keyin.airline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirlineFormattedDTOTest {

    @Test
    void testConstructor() {
        AirlineFormattedDTO dto = new AirlineFormattedDTO("Test Airline");
        assertEquals("Test Airline", dto.getName());
    }

    @Test
    void testSetName() {
        AirlineFormattedDTO dto = new AirlineFormattedDTO("Test Airline");
        dto.setName("Updated Airline");
        assertEquals("Updated Airline", dto.getName());
    }
}