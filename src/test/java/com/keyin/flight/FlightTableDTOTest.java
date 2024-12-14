package com.keyin.flight;

import com.keyin.aircraft.AircraftFormattedDTO;
import com.keyin.airport.AirportFormattedDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTableDTOTest {
    private FlightTableDTO flightTableDTO;
    private AirportFormattedDTO originAirport;
    private AirportFormattedDTO destinationAirport;
    private AircraftFormattedDTO aircraft;

    @BeforeEach
    void setUp() {
        originAirport = new AirportFormattedDTO("JFK", "John F. Kennedy International Airport");
        destinationAirport = new AirportFormattedDTO("LAX", "Los Angeles International Airport");
        aircraft = new AircraftFormattedDTO(1, "Boeing 737");

        flightTableDTO = new FlightTableDTO(
                101,
                "2024-12-20T10:00:00Z",
                "2024-12-20T14:00:00Z",
                originAirport,
                destinationAirport,
                aircraft,
                150);
    }

    @Test
    void testFlightTableDTOCreation() {
        assertNotNull(flightTableDTO);
        assertEquals(101, flightTableDTO.getId());
        assertEquals("2024-12-20T10:00:00Z", flightTableDTO.getDeparture());
        assertEquals("2024-12-20T14:00:00Z", flightTableDTO.getArrival());
        assertEquals(originAirport, flightTableDTO.getOrigin());
        assertEquals(destinationAirport, flightTableDTO.getDestination());
        assertEquals(aircraft, flightTableDTO.getAircraft());
        assertEquals(150, flightTableDTO.getNumberOfPassengers());
    }

    @Test
    void testSetters() {
        AirportFormattedDTO newOrigin = new AirportFormattedDTO("SFO", "San Francisco International Airport");
        AirportFormattedDTO newDestination = new AirportFormattedDTO("SEA", "Seattle-Tacoma International Airport");
        AircraftFormattedDTO newAircraft = new AircraftFormattedDTO(2, "Airbus A320");

        flightTableDTO.setId(202);
        flightTableDTO.setDeparture("2024-12-25T12:00:00Z");
        flightTableDTO.setArrival("2024-12-25T16:00:00Z");
        flightTableDTO.setOrigin(newOrigin);
        flightTableDTO.setDestination(newDestination);
        flightTableDTO.setAircraft(newAircraft);
        flightTableDTO.setNumberOfPassengers(180);

        assertEquals(202, flightTableDTO.getId());
        assertEquals("2024-12-25T12:00:00Z", flightTableDTO.getDeparture());
        assertEquals("2024-12-25T16:00:00Z", flightTableDTO.getArrival());
        assertEquals(newOrigin, flightTableDTO.getOrigin());
        assertEquals(newDestination, flightTableDTO.getDestination());
        assertEquals(newAircraft, flightTableDTO.getAircraft());
        assertEquals(180, flightTableDTO.getNumberOfPassengers());
    }
}