package com.keyin.airline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AirlineRepositoryTest {

    @Autowired
    private AirlineRepository airlineRepository;

    private Airline testAirline;

    @BeforeEach
    void setUp() {
        testAirline = new Airline("Test Airline", "Test Country");
        airlineRepository.save(testAirline);
    }

    @Test
    void testFindAllAirlines() {
        Iterable<Airline> airlines = airlineRepository.findAll();
        assertNotNull(airlines);
        assertTrue(airlines.iterator().hasNext());
    }

    @Test
    void testFindByName() {
        Airline foundAirline = airlineRepository.findByName(testAirline.getName());
        assertNotNull(foundAirline);
        assertEquals(testAirline.getName(), foundAirline.getName());
        assertEquals(testAirline.getCountry(), foundAirline.getCountry());
    }

    @Test
    void testSaveAirline() {
        Airline newAirline = new Airline("New Airline", "New Country");
        Airline savedAirline = airlineRepository.save(newAirline);

        assertNotNull(savedAirline);
        assertNotNull(savedAirline.getId());
        assertEquals("New Airline", savedAirline.getName());
        assertEquals("New Country", savedAirline.getCountry());
    }

    @Test
    void testDeleteAirline() {
        airlineRepository.delete(testAirline);
        Airline deletedAirline = airlineRepository.findByName(testAirline.getName());
        assertNull(deletedAirline);
    }

    @Test
    void testUpdateAirline() {
        testAirline.setName("Updated Airline");
        testAirline.setCountry("Updated Country");
        Airline updatedAirline = airlineRepository.save(testAirline);

        assertNotNull(updatedAirline);
        assertEquals("Updated Airline", updatedAirline.getName());
        assertEquals("Updated Country", updatedAirline.getCountry());
    }
}