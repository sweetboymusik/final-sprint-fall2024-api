package com.keyin.airport;

import com.keyin.city.CityFormattedDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportTableDTOTest {

    @Test
    void testAirportTableDTOCreationAndGetters() {
        int id = 1;
        String name = "Test Airport";
        String code = "TST";
        CityFormattedDTO city = new CityFormattedDTO("Test City", "Test State");
        int gateCount = 5;

        AirportTableDTO airportTableDTO = new AirportTableDTO(id, name, code, city, gateCount);

        assertEquals(id, airportTableDTO.getId());
        assertEquals(name, airportTableDTO.getName());
        assertEquals(code, airportTableDTO.getCode());
        assertEquals(city, airportTableDTO.getCity());
        assertEquals(gateCount, airportTableDTO.getGateCount());
    }

    @Test
    void testSetters() {
        AirportTableDTO airportTableDTO = new AirportTableDTO(0, null, null, null, 0);

        int id = 2;
        String name = "Updated Airport";
        String code = "UPD";
        CityFormattedDTO city = new CityFormattedDTO("Updated City", "Updated State");
        int gateCount = 10;

        airportTableDTO.setId(id);
        airportTableDTO.setName(name);
        airportTableDTO.setCode(code);
        airportTableDTO.setCity(city);
        airportTableDTO.setGateCount(gateCount);

        assertEquals(id, airportTableDTO.getId());
        assertEquals(name, airportTableDTO.getName());
        assertEquals(code, airportTableDTO.getCode());
        assertEquals(city, airportTableDTO.getCity());
        assertEquals(gateCount, airportTableDTO.getGateCount());
    }
}