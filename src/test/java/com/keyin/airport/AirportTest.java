package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.gate.Gate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    @Test
    void testAirportConstructorAndGetters() {
        City city = new City("Test City", "Test State", 100000);
        Airport airport = new Airport("Test Airport", "TST", city);

        assertEquals("Test Airport", airport.getName());
        assertEquals("TST", airport.getCode());
        assertEquals(city, airport.getCity());
        assertNotNull(airport.getGates());
        assertTrue(airport.getGates().isEmpty());
    }

    @Test
    void testSetters() {
        City city = new City("Test City", "Test State", 100000);
        City newCity = new City("New City", "New State", 200000);
        Airport airport = new Airport("Test Airport", "TST", city);

        airport.setName("Updated Airport");
        airport.setCode("UPD");
        airport.setCity(newCity);

        assertEquals("Updated Airport", airport.getName());
        assertEquals("UPD", airport.getCode());
        assertEquals(newCity, airport.getCity());
    }

    @Test
    void testAddAndSetGates() {
        Airport airport = new Airport();
        List<Gate> gates = new ArrayList<>();
        gates.add(new Gate("A1", airport));
        gates.add(new Gate("A2", airport));

        airport.setGates(gates);

        assertNotNull(airport.getGates());
        assertEquals(2, airport.getGates().size());
        assertEquals("A1", airport.getGates().get(0).getGateNumber());
        assertEquals("A2", airport.getGates().get(1).getGateNumber());
    }

    @Test
    void testEmptyConstructor() {
        Airport airport = new Airport();

        assertNull(airport.getName());
        assertNull(airport.getCode());
        assertNull(airport.getCity());
        assertNotNull(airport.getGates());
        assertTrue(airport.getGates().isEmpty());
    }
}