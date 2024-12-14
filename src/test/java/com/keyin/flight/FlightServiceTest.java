package com.keyin.flight;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftService;
import com.keyin.airport.Airport;
import com.keyin.airline.Airline;
import com.keyin.city.City;
import com.keyin.gate.Gate;
import com.keyin.gate.GateRepository;
import com.keyin.passenger.Passenger;
import com.keyin.passenger.PassengerRepository;
import com.keyin.passenger.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FlightServiceTest {
    @Mock
    private FlightRepository flightRepository;

    @Mock
    private PassengerService passengerService;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private GateRepository gateRepository;

    @Mock
    private AircraftService aircraftService;

    @InjectMocks
    private FlightService flightService;

    private Flight testFlight;
    private Gate testOriginGate;
    private Gate testDestinationGate;
    private Aircraft testAircraft;
    private Passenger testPassenger;
    private City testCity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testCity = new City("TestCity", "TestState", 100000);
        Airport testAirport = new Airport("TestAirport", "TST", testCity);

        testOriginGate = new Gate("Gate A1", testAirport);
        testDestinationGate = new Gate("Gate B2", testAirport);

        testAircraft = new Aircraft("Boeing 737", 200, null);

        testFlight = new Flight(
                LocalDateTime.of(2024, 12, 25, 10, 0),
                LocalDateTime.of(2024, 12, 25, 14, 0),
                testOriginGate,
                testDestinationGate,
                testAircraft,
                0);

        testPassenger = new Passenger("John", "Doe", "1234567890", "john.doe@example.com", testCity);
    }

    @Test
    void testAddFlight() {
        Airport originAirport = new Airport("Origin Airport", "ORI", testCity);
        originAirport.setId(1);

        Airport destinationAirport = new Airport("Destination Airport", "DES", testCity);
        destinationAirport.setId(2);

        Gate originGate = new Gate("Gate A1", originAirport);
        originGate.setId(1);

        Gate destinationGate = new Gate("Gate B2", destinationAirport);
        destinationGate.setId(2);

        FlightDTO flightDTO = new FlightDTO(
                LocalDateTime.of(2024, 12, 25, 10, 0),
                LocalDateTime.of(2024, 12, 25, 14, 0),
                originGate.getId(), destinationGate.getId(),
                3, 0);

        when(gateRepository.findById(originGate.getId())).thenReturn(Optional.of(originGate));
        when(gateRepository.findById(destinationGate.getId())).thenReturn(Optional.of(destinationGate));
        when(aircraftService.getAircraftById(3)).thenReturn(testAircraft);
        when(flightRepository.save(any(Flight.class))).thenReturn(testFlight);

        Flight addedFlight = flightService.addFlight(flightDTO);

        assertNotNull(addedFlight);
        assertEquals(testFlight.getDeparture(), addedFlight.getDeparture());
        assertEquals(testFlight.getArrival(), addedFlight.getArrival());
        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    void testAddPassengerById() {
        when(flightRepository.findById(1)).thenReturn(Optional.of(testFlight));
        when(passengerService.getPassengerById(1)).thenReturn(testPassenger);
        when(passengerRepository.save(any(Passenger.class))).thenReturn(testPassenger);

        String result = flightService.addPassengerById(1, 1);

        assertEquals("Passenger added to flight successfully", result);
        verify(passengerRepository, times(1)).save(any(Passenger.class));
    }

    @Test
    void testGetFlightById() {
        when(flightRepository.findById(1)).thenReturn(Optional.of(testFlight));

        Flight flight = flightService.getFlightById(1);

        assertNotNull(flight);
        assertEquals(testFlight.getId(), flight.getId());
        verify(flightRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateFlightById() {
        FlightDTO flightDTO = new FlightDTO(
                LocalDateTime.of(2024, 12, 26, 10, 0),
                LocalDateTime.of(2024, 12, 26, 14, 0),
                1, 2, 3, 100);

        when(flightRepository.findById(1)).thenReturn(Optional.of(testFlight));
        when(gateRepository.findById(1)).thenReturn(Optional.of(testOriginGate));
        when(gateRepository.findById(2)).thenReturn(Optional.of(testDestinationGate));
        when(aircraftService.getAircraftById(3)).thenReturn(testAircraft);
        when(flightRepository.save(any(Flight.class))).thenReturn(testFlight);

        Flight updatedFlight = flightService.updateFlightById(1, flightDTO);

        assertNotNull(updatedFlight);
        assertEquals(flightDTO.getNumberOfPassengers(), updatedFlight.getNumberOfPassengers());
        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    void testDeleteFlightById() {
        when(flightRepository.findById(1)).thenReturn(Optional.of(testFlight));

        flightService.deleteFlightById(1);

        verify(flightRepository, times(1)).delete(testFlight);
    }

    @Test
    void testGetAllFlightsForTable() {
        Airline testAirline = new Airline("Boeing Airline", "USA");
        testAirline.setId(1);

        Aircraft testAircraft = new Aircraft("Boeing 737", 180, testAirline);
        testAircraft.setId(1);

        Airport originAirport = new Airport("Origin Airport", "ORI", testCity);
        originAirport.setId(1);

        Airport destinationAirport = new Airport("Destination Airport", "DES", testCity);
        destinationAirport.setId(2);

        Gate originGate = new Gate("Gate A1", originAirport);
        originGate.setId(1);

        Gate destinationGate = new Gate("Gate B2", destinationAirport);
        destinationGate.setId(2);

        Flight testFlight = new Flight(
                LocalDateTime.of(2024, 12, 25, 10, 0),
                LocalDateTime.of(2024, 12, 25, 14, 0),
                originGate,
                destinationGate,
                testAircraft,
                120);
        testFlight.setId(1);

        List<Flight> flightList = List.of(testFlight);

        when(flightRepository.findAll()).thenReturn(flightList);

        Iterable<FlightTableDTO> flightTableDTOS = flightService.getAllFlightsForTable();

        assertNotNull(flightTableDTOS);

        FlightTableDTO dto = flightTableDTOS.iterator().next();
        assertNotNull(dto);
        assertEquals("Boeing 737 (ID: 1) | Boeing Airline", dto.getAircraft().getNameFormatted());
        verify(flightRepository, times(1)).findAll();
    }
}