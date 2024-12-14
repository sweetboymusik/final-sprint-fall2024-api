package com.keyin.gate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GateTableDTOTest {

    @Test
    void testConstructorAndGetters() {
        GateTableDTO gateTableDTO = new GateTableDTO(1, "A1", 10, 5);

        assertEquals(1, gateTableDTO.getId());
        assertEquals("A1", gateTableDTO.getGateNumber());
        assertEquals(10, gateTableDTO.getOriginFlights());
        assertEquals(5, gateTableDTO.getDestinationFlights());
    }

    @Test
    void testSetters() {
        GateTableDTO gateTableDTO = new GateTableDTO(1, "A1", 10, 5);

        gateTableDTO.setId(2);
        gateTableDTO.setGateNumber("B2");
        gateTableDTO.setOriginFlights(15);
        gateTableDTO.setDestinationFlights(8);

        assertEquals(2, gateTableDTO.getId());
        assertEquals("B2", gateTableDTO.getGateNumber());
        assertEquals(15, gateTableDTO.getOriginFlights());
        assertEquals(8, gateTableDTO.getDestinationFlights());
    }
}