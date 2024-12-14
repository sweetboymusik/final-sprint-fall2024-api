package com.keyin.gate;

import com.keyin.airport.AirportSingleDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GateFlightDTOTest {

    @Test
    void testConstructorAndGetters() {
        AirportSingleDTO airport = new AirportSingleDTO(1, "JFK Airport", "JFK", null);
        GateFlightDTO gateFlightDTO = new GateFlightDTO(1, "A1", airport);

        assertEquals(1, gateFlightDTO.getId());
        assertEquals("A1", gateFlightDTO.getGateNumber());
        assertEquals(airport, gateFlightDTO.getAirport());
    }

    @Test
    void testSetters() {
        AirportSingleDTO initialAirport = new AirportSingleDTO(1, "JFK Airport", "JFK", null);
        AirportSingleDTO updatedAirport = new AirportSingleDTO(2, "LAX Airport", "LAX", null);

        GateFlightDTO gateFlightDTO = new GateFlightDTO(1, "A1", initialAirport);

        gateFlightDTO.setId(2);
        gateFlightDTO.setGateNumber("B2");
        gateFlightDTO.setAirport(updatedAirport);

        assertEquals(2, gateFlightDTO.getId());
        assertEquals("B2", gateFlightDTO.getGateNumber());
        assertEquals(updatedAirport, gateFlightDTO.getAirport());
    }
}