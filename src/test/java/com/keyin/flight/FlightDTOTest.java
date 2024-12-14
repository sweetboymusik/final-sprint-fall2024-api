package com.keyin.flight;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightDTOTest {

    @Test
    void testConstructorAndGetters() {
        LocalDateTime departure = LocalDateTime.of(2024, 12, 25, 10, 0);
        LocalDateTime arrival = LocalDateTime.of(2024, 12, 25, 14, 0);
        int originGateId = 1;
        int destinationGateId = 2;
        int aircraftId = 3;
        int numberOfPassengers = 150;

        FlightDTO flightDTO = new FlightDTO(departure, arrival, originGateId, destinationGateId, aircraftId,
                numberOfPassengers);

        assertEquals(departure, flightDTO.getDeparture());
        assertEquals(arrival, flightDTO.getArrival());
        assertEquals(originGateId, flightDTO.getOriginGateId());
        assertEquals(destinationGateId, flightDTO.getDestinationGateId());
        assertEquals(aircraftId, flightDTO.getAircraftId());
        assertEquals(numberOfPassengers, flightDTO.getNumberOfPassengers());
    }

    @Test
    void testSetters() {
        LocalDateTime departure = LocalDateTime.of(2024, 12, 25, 10, 0);
        LocalDateTime arrival = LocalDateTime.of(2024, 12, 25, 14, 0);
        int originGateId = 1;
        int destinationGateId = 2;
        int aircraftId = 3;
        int numberOfPassengers = 150;

        FlightDTO flightDTO = new FlightDTO(null, null, 0, 0, 0, 0);

        flightDTO.setDeparture(departure);
        flightDTO.setArrival(arrival);
        flightDTO.setOriginGateId(originGateId);
        flightDTO.setDestinationGateId(destinationGateId);
        flightDTO.setAircraftId(aircraftId);
        flightDTO.setNumberOfPassengers(numberOfPassengers);

        assertEquals(departure, flightDTO.getDeparture());
        assertEquals(arrival, flightDTO.getArrival());
        assertEquals(originGateId, flightDTO.getOriginGateId());
        assertEquals(destinationGateId, flightDTO.getDestinationGateId());
        assertEquals(aircraftId, flightDTO.getAircraftId());
        assertEquals(numberOfPassengers, flightDTO.getNumberOfPassengers());
    }
}