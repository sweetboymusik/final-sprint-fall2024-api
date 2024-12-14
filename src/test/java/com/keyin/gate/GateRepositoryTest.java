package com.keyin.gate;

import com.keyin.airport.Airport;
import com.keyin.airport.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GateRepositoryTest {

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private AirportRepository airportRepository;

    private Airport testAirport;

    @BeforeEach
    void setUp() {
        gateRepository.deleteAll();
        airportRepository.deleteAll();

        testAirport = airportRepository.save(new Airport("Test Airport", "TST", null));
    }

    @Test
    void testSaveGate() {
        Gate gate = new Gate("A1", testAirport);
        Gate savedGate = gateRepository.save(gate);

        assertNotNull(savedGate);
        assertEquals("A1", savedGate.getGateNumber());
        assertTrue(savedGate.getId() > 0);
        assertEquals(testAirport.getId(), savedGate.getAirport().getId());
    }

    @Test
    void testFindByAirport() {
        Gate gate1 = new Gate("A1", testAirport);
        Gate gate2 = new Gate("B1", testAirport);
        gateRepository.save(gate1);
        gateRepository.save(gate2);

        List<Gate> gates = gateRepository.findByAirport(testAirport);

        assertNotNull(gates);
        assertEquals(2, gates.size());
        assertTrue(gates.stream().anyMatch(g -> g.getGateNumber().equals("A1")));
        assertTrue(gates.stream().anyMatch(g -> g.getGateNumber().equals("B1")));
    }
}