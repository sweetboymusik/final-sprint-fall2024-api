package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.city.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AirportRepositoryTest {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    void testSaveAndRetrieveAirport() {
        City city = new City("Test City", "Test State", 500000);
        city = cityRepository.save(city);

        Airport airport = new Airport("Test Airport", "TST", city);
        Airport savedAirport = airportRepository.save(airport);

        assertNotNull(savedAirport.getId(), "Saved Airport should have an ID");
        assertEquals("Test Airport", savedAirport.getName(), "Airport name should match");
        assertEquals("TST", savedAirport.getCode(), "Airport code should match");
        assertEquals(city.getId(), savedAirport.getCity().getId(), "City ID should match");
    }

    @Test
    void testFindAllAirports() {
        City city = new City("Test City", "Test State", 500000);
        city = cityRepository.save(city);

        Airport airport = new Airport("Test Airport", "TST", city);
        airportRepository.save(airport);

        Iterable<Airport> airports = airportRepository.findAll();
        assertNotNull(airports, "Airports should not be null");
        assertTrue(airports.iterator().hasNext(), "Airports should contain at least one element");
    }

    @Test
    void testDeleteAirport() {
        City city = new City("Test City", "Test State", 500000);
        city = cityRepository.save(city);

        Airport airport = new Airport("Test Airport", "TST", city);
        airport = airportRepository.save(airport);

        assertTrue(airportRepository.findById(airport.getId()).isPresent(), "Airport should exist before deletion");

        airportRepository.delete(airport);

        assertFalse(airportRepository.findById(airport.getId()).isPresent(),
                "Airport should no longer exist after deletion");
    }
}