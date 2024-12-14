package com.keyin.gate;

import com.keyin.airport.Airport;
import com.keyin.flight.Flight;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GateTest {

    @Test
    void testGateConstructorAndGetters() {
        Airport airport = new Airport();
        airport.setId(1);
        airport.setName("Test Airport");
        airport.setCode("TST");

        Gate gate = new Gate("A1", airport);

        assertEquals("A1", gate.getGateNumber());
        assertEquals(airport, gate.getAirport());
    }

    @Test
    void testSetters() {
        Airport airport = new Airport();
        airport.setId(1);
        airport.setName("Test Airport");
        airport.setCode("TST");

        Gate gate = new Gate();
        gate.setGateNumber("B2");
        gate.setAirport(airport);

        assertEquals("B2", gate.getGateNumber());
        assertEquals(airport, gate.getAirport());
    }

    @Test
    void testFlightLists() {
        Gate gate = new Gate("C3", null);

        Flight flight1 = new Flight();
        flight1.setId(1);

        Flight flight2 = new Flight();
        flight2.setId(2);

        List<Flight> originFlights = new ArrayList<>();
        originFlights.add(flight1);

        List<Flight> destinationFlights = new ArrayList<>();
        destinationFlights.add(flight2);

        gate.setOriginFlights(originFlights);
        gate.setDestinationFlights(destinationFlights);

        assertEquals(1, gate.getOriginFlights().size());
        assertEquals(flight1, gate.getOriginFlights().get(0));

        assertEquals(1, gate.getDestinationFlights().size());
        assertEquals(flight2, gate.getDestinationFlights().get(0));
    }
}