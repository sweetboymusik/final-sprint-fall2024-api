package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.gate.GateTableDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportSingleDTOTest {

    @Test
    void testConstructorWithAirportAndGates() {
        City city = new City("Sample City", "Sample State", 123456);
        Airport airport = new Airport("Sample Airport", "SMP", city);

        List<GateTableDTO> gates = new ArrayList<>();
        gates.add(new GateTableDTO(1, "Gate 1", 3, 2));
        gates.add(new GateTableDTO(2, "Gate 2", 4, 1));

        AirportSingleDTO airportSingleDTO = new AirportSingleDTO(airport, gates);

        assertEquals(airport.getId(), airportSingleDTO.getId());
        assertEquals(airport.getName(), airportSingleDTO.getName());
        assertEquals(airport.getCode(), airportSingleDTO.getCode());
        assertEquals(airport.getCity(), airportSingleDTO.getCity());
        assertEquals(gates, airportSingleDTO.getGates());
    }

    @Test
    void testConstructorWithBasicFields() {
        City city = new City("Sample City", "Sample State", 123456);

        AirportSingleDTO airportSingleDTO = new AirportSingleDTO(1, "Sample Airport", "SMP", city);

        assertEquals(1, airportSingleDTO.getId());
        assertEquals("Sample Airport", airportSingleDTO.getName());
        assertEquals("SMP", airportSingleDTO.getCode());
        assertEquals(city, airportSingleDTO.getCity());
        assertNull(airportSingleDTO.getGates());
    }

    @Test
    void testSettersAndGetters() {
        City city = new City("Sample City", "Sample State", 123456);
        List<GateTableDTO> gates = new ArrayList<>();
        gates.add(new GateTableDTO(1, "Gate 1", 3, 2));

        AirportSingleDTO airportSingleDTO = new AirportSingleDTO(1, "Initial Airport", "INIT", city);

        airportSingleDTO.setId(2);
        airportSingleDTO.setName("Updated Airport");
        airportSingleDTO.setCode("UPDT");
        airportSingleDTO.setCity(new City("Updated City", "Updated State", 654321));
        airportSingleDTO.setGates(gates);

        assertEquals(2, airportSingleDTO.getId());
        assertEquals("Updated Airport", airportSingleDTO.getName());
        assertEquals("UPDT", airportSingleDTO.getCode());
        assertEquals("Updated City", airportSingleDTO.getCity().getName());
        assertEquals(gates, airportSingleDTO.getGates());
    }
}