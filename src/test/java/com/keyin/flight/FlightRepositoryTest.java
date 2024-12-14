package com.keyin.flight;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftRepository;
import com.keyin.airline.Airline;
import com.keyin.airline.AirlineRepository;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportRepository;
import com.keyin.city.City;
import com.keyin.city.CityRepository;
import com.keyin.gate.Gate;
import com.keyin.gate.GateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    private Gate originGate;
    private Gate destinationGate;
    private Aircraft aircraft;

    @BeforeEach
    void setup() {
        City city = cityRepository.save(new City("Test City", "Test State", 1000000));
        Airport airport = airportRepository.save(new Airport("Test Airport", "TST", city));
        originGate = gateRepository.save(new Gate("Gate A1", airport));
        destinationGate = gateRepository.save(new Gate("Gate B1", airport));
        Airline airline = airlineRepository.save(new Airline("Test Airline", "USA"));
        aircraft = aircraftRepository.save(new Aircraft("Boeing 737", 180, airline));

        Flight flight = new Flight(
                LocalDateTime.of(2024, 12, 25, 10, 0),
                LocalDateTime.of(2024, 12, 25, 14, 0),
                originGate,
                destinationGate,
                aircraft,
                120);

        flightRepository.save(flight);
    }

    @Test
    void testFindAllFlights() {
        Iterable<Flight> flights = flightRepository.findAll();
        assertNotNull(flights);
        assertTrue(flights.iterator().hasNext());
    }

    @Test
    void testSaveFlight() {
        Flight flight = new Flight(
                LocalDateTime.of(2025, 1, 1, 8, 0),
                LocalDateTime.of(2025, 1, 1, 12, 0),
                originGate,
                destinationGate,
                aircraft,
                150);

        Flight savedFlight = flightRepository.save(flight);
        assertNotNull(savedFlight);
        assertTrue(savedFlight.getId() > 0);
    }

    @Test
    void testDeleteFlight() {
        Flight flight = flightRepository.findAll().iterator().next();
        assertNotNull(flight);

        flightRepository.delete(flight);

        Iterable<Flight> flightsAfterDeletion = flightRepository.findAll();
        assertFalse(flightsAfterDeletion.iterator().hasNext());
    }
}