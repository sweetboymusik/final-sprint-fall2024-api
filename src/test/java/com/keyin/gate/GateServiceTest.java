package com.keyin.gate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GateServiceTest {

    private GateRepository gateRepository;
    private GateService gateService;

    @BeforeEach
    void setUp() {
        gateRepository = Mockito.mock(GateRepository.class);
        gateService = new GateService(gateRepository);
    }

    @Test
    void testAddGate() {
        Gate gate = new Gate("A1", null);

        when(gateRepository.save(gate)).thenReturn(gate);

        Gate savedGate = gateService.addGate(gate);

        assertNotNull(savedGate);
        assertEquals("A1", savedGate.getGateNumber());
        verify(gateRepository, times(1)).save(gate);
    }

    @Test
    void testDeleteGate() {
        int gateId = 1;

        doNothing().when(gateRepository).deleteById(gateId);

        gateService.deleteGate(gateId);

        verify(gateRepository, times(1)).deleteById(gateId);
    }
}