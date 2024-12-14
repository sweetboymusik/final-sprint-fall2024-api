package com.keyin.gate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GateDTOTest {

    @Test
    void testConstructorAndGetters() {
        GateDTO gateDTO = new GateDTO(1, "A1");

        assertEquals(1, gateDTO.getId());
        assertEquals("A1", gateDTO.getGateNumber());
    }

    @Test
    void testSetters() {
        GateDTO gateDTO = new GateDTO(0, null);

        gateDTO.setId(2);
        gateDTO.setGateNumber("B2");

        assertEquals(2, gateDTO.getId());
        assertEquals("B2", gateDTO.getGateNumber());
    }
}