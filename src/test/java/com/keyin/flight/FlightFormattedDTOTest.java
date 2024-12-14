package com.keyin.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightFormattedDTOTest {
    private FlightFormattedDTO flightFormattedDTO;

    @BeforeEach
    void setUp() {
        flightFormattedDTO = new FlightFormattedDTO(1, "JFK (New York, NY)", "LAX (Los Angeles, CA)");
    }

    @Test
    void testFlightFormattedDTOCreation() {
        assertNotNull(flightFormattedDTO);
        assertEquals("Flight #1 | JFK (New York, NY) → LAX (Los Angeles, CA)", flightFormattedDTO.getFlightFormatted());
    }

    @Test
    void testSetFlightFormatted() {
        String newFormatted = "Flight #2 | SFO (San Francisco, CA) → SEA (Seattle, WA)";
        flightFormattedDTO.setFlightFormatted(newFormatted);

        assertEquals(newFormatted, flightFormattedDTO.getFlightFormatted());
    }
}