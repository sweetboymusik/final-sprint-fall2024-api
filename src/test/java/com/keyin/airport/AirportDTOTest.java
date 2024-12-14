package com.keyin.airport;

import com.keyin.gate.GateDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportDTOTest {

    @Test
    void testAirportDTOCreationAndGetters() {
        String name = "Test Airport";
        String code = "TST";
        int cityId = 1;
        List<GateDTO> gates = Arrays.asList(
                new GateDTO(1, "Gate 1"),
                new GateDTO(2, "Gate 2"));

        AirportDTO airportDTO = new AirportDTO(name, code, cityId, gates);

        assertEquals(name, airportDTO.getName());
        assertEquals(code, airportDTO.getCode());
        assertEquals(cityId, airportDTO.getCityId());
        assertEquals(gates, airportDTO.getGates());
    }

    @Test
    void testSetters() {
        AirportDTO airportDTO = new AirportDTO(null, null, 0, null);

        String name = "Updated Airport";
        String code = "UPD";
        int cityId = 2;
        List<GateDTO> gates = Arrays.asList(
                new GateDTO(3, "Gate 3"),
                new GateDTO(4, "Gate 4"));

        airportDTO.setName(name);
        airportDTO.setCode(code);
        airportDTO.setCityId(cityId);
        airportDTO.setGates(gates);

        assertEquals(name, airportDTO.getName());
        assertEquals(code, airportDTO.getCode());
        assertEquals(cityId, airportDTO.getCityId());
        assertEquals(gates, airportDTO.getGates());
    }
}