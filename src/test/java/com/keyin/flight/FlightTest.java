package com.keyin.flight;

import com.keyin.aircraft.Aircraft;
import com.keyin.gate.Gate;
import com.keyin.passenger.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    private Flight testFlight;
    private Gate originGate;
    private Gate destinationGate;
    private Aircraft aircraft;

    @BeforeEach
    void setUp() {
        originGate = new Gate();
        originGate.setId(1);
        originGate.setGateNumber("A1");

        destinationGate = new Gate();
        destinationGate.setId(2);
        destinationGate.setGateNumber("B1");

        aircraft = new Aircraft();
        aircraft.setId(1);
        aircraft.setType("Boeing 737");
        aircraft.setPassengerCapacity(180);

        testFlight = new Flight(
                LocalDateTime.of(2024, 12, 15, 10, 30),
                LocalDateTime.of(2024, 12, 15, 13, 30),
                originGate,
                destinationGate,
                aircraft,
                150);
    }

    @Test
    void testFlightCreation() {
        assertNotNull(testFlight);
        assertEquals(LocalDateTime.of(2024, 12, 15, 10, 30), testFlight.getDeparture());
        assertEquals(LocalDateTime.of(2024, 12, 15, 13, 30), testFlight.getArrival());
        assertEquals(originGate, testFlight.getOriginGate());
        assertEquals(destinationGate, testFlight.getDestinationGate());
        assertEquals(aircraft, testFlight.getAircraft());
        assertEquals(150, testFlight.getNumberOfPassengers());
        assertNotNull(testFlight.getPassengerList());
        assertTrue(testFlight.getPassengerList().isEmpty());
    }

    @Test
    void testSetAndGetPassengerList() {
        Passenger passenger1 = new Passenger();
        passenger1.setId(1);
        passenger1.setFirstName("John");
        passenger1.setLastName("Doe");

        Passenger passenger2 = new Passenger();
        passenger2.setId(2);
        passenger2.setFirstName("Jane");
        passenger2.setLastName("Smith");

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger1);
        passengers.add(passenger2);

        testFlight.setPassengerList(passengers);

        assertNotNull(testFlight.getPassengerList());
        assertEquals(2, testFlight.getPassengerList().size());
        assertTrue(testFlight.getPassengerList().contains(passenger1));
        assertTrue(testFlight.getPassengerList().contains(passenger2));
        assertEquals("John", testFlight.getPassengerList().get(0).getFirstName());
        assertEquals("Doe", testFlight.getPassengerList().get(0).getLastName());
        assertEquals("Jane", testFlight.getPassengerList().get(1).getFirstName());
        assertEquals("Smith", testFlight.getPassengerList().get(1).getLastName());
    }

    @Test
    void testSetAndGetNumberOfPassengers() {
        testFlight.setNumberOfPassengers(200);
        assertEquals(200, testFlight.getNumberOfPassengers());
    }

    @Test
    void testSetAndGetGates() {
        Gate newOriginGate = new Gate();
        newOriginGate.setId(3);
        newOriginGate.setGateNumber("C1");

        Gate newDestinationGate = new Gate();
        newDestinationGate.setId(4);
        newDestinationGate.setGateNumber("D1");

        testFlight.setOriginGate(newOriginGate);
        testFlight.setDestinationGate(newDestinationGate);

        assertEquals(newOriginGate, testFlight.getOriginGate());
        assertEquals(newDestinationGate, testFlight.getDestinationGate());
    }

    @Test
    void testSetAndGetAircraft() {
        Aircraft newAircraft = new Aircraft();
        newAircraft.setId(2);
        newAircraft.setType("Airbus A320");
        newAircraft.setPassengerCapacity(200);

        testFlight.setAircraft(newAircraft);

        assertEquals(newAircraft, testFlight.getAircraft());
    }
}