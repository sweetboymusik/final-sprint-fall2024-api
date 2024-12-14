package com.keyin.aircraft;

import com.keyin.airline.Airline;
import com.keyin.airline.AirlineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AircraftRepositoryTest {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    private Airline testAirline;
    private Aircraft testAircraft;

    @BeforeEach
    void setUp() {
        testAirline = new Airline("Air Canada", "Canada");
        testAirline = airlineRepository.save(testAirline);

        testAircraft = new Aircraft("Boeing 737", 200, testAirline);
        testAircraft = aircraftRepository.save(testAircraft);
    }

    @Test
    void testSaveAircraft() {
        Aircraft aircraft = new Aircraft("Airbus A320", 180, testAirline);
        Aircraft savedAircraft = aircraftRepository.save(aircraft);

        assertNotNull(savedAircraft.getId());
        assertEquals("Airbus A320", savedAircraft.getType());
        assertEquals(180, savedAircraft.getPassengerCapacity());
        assertEquals("Air Canada", savedAircraft.getAirline().getName());
    }

    @Test
    void testFindAircraftById() {
        Optional<Aircraft> foundAircraft = aircraftRepository.findById(testAircraft.getId());

        assertTrue(foundAircraft.isPresent());
        assertEquals(testAircraft.getId(), foundAircraft.get().getId());
        assertEquals("Boeing 737", foundAircraft.get().getType());
    }

    @Test
    void testFindAllAircraft() {
        Iterable<Aircraft> aircraftList = aircraftRepository.findAll();
        assertNotNull(aircraftList);
        assertTrue(aircraftList.iterator().hasNext());
    }

    @Test
    void testDeleteAircraft() {
        aircraftRepository.delete(testAircraft);

        Optional<Aircraft> deletedAircraft = aircraftRepository.findById(testAircraft.getId());
        assertFalse(deletedAircraft.isPresent());
    }
}