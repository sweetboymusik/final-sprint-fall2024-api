package com.keyin.airline;

import com.keyin.aircraft.Aircraft;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirlineTest {
    private Airline airline;

    @BeforeEach
    void setUp() {
        airline = new Airline("Test Airline", "Test Country");
    }

    @Test
    void testAirlineConstructorAndGetters() {
        assertEquals("Test Airline", airline.getName());
        assertEquals("Test Country", airline.getCountry());
        assertNull(airline.getAircraftList(), "Aircraft list should be null by default");
    }

    @Test
    void testSetters() {
        airline.setName("Updated Airline");
        airline.setCountry("Updated Country");
        assertEquals("Updated Airline", airline.getName());
        assertEquals("Updated Country", airline.getCountry());
    }

    @Test
    void testSetAndGetAircraftList() {
        List<Aircraft> aircraftList = new ArrayList<>();
        Aircraft aircraft1 = new Aircraft();
        Aircraft aircraft2 = new Aircraft();

        aircraftList.add(aircraft1);
        aircraftList.add(aircraft2);

        airline.setAircraftList(aircraftList);

        assertNotNull(airline.getAircraftList());
        assertEquals(2, airline.getAircraftList().size());
        assertTrue(airline.getAircraftList().contains(aircraft1));
        assertTrue(airline.getAircraftList().contains(aircraft2));
    }
}
