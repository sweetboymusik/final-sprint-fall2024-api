package com.keyin.flight;

import com.keyin.aircraft.Aircraft;
import com.keyin.gate.GateFlightDTO;
import com.keyin.passenger.Passenger;
import com.keyin.airport.AirportSingleDTO;
import com.keyin.city.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightDetailsDTOTest {
    private FlightDetailsDTO flightDetailsDTO;
    private GateFlightDTO originGate;
    private GateFlightDTO destinationGate;
    private Aircraft aircraft;
    private List<Passenger> passengers;

    @BeforeEach
    void setUp() {
        City city = new City("New York", "NY", 8000000);
        AirportSingleDTO originAirport = new AirportSingleDTO(1, "JFK", "JFK Code", city);
        AirportSingleDTO destinationAirport = new AirportSingleDTO(2, "LAX", "LAX Code", city);

        originGate = new GateFlightDTO(1, "A1", originAirport);
        destinationGate = new GateFlightDTO(2, "B1", destinationAirport);

        aircraft = new Aircraft();
        aircraft.setId(1);
        aircraft.setType("Boeing 737");
        aircraft.setPassengerCapacity(180);

        passengers = new ArrayList<>();
        Passenger passenger1 = new Passenger();
        passenger1.setId(1);
        passenger1.setFirstName("John");
        passenger1.setLastName("Doe");

        Passenger passenger2 = new Passenger();
        passenger2.setId(2);
        passenger2.setFirstName("Jane");
        passenger2.setLastName("Smith");

        passengers.add(passenger1);
        passengers.add(passenger2);

        flightDetailsDTO = new FlightDetailsDTO(
                1,
                LocalDateTime.of(2024, 12, 15, 10, 30),
                LocalDateTime.of(2024, 12, 15, 13, 30),
                originGate,
                destinationGate,
                passengers,
                aircraft);
    }

    @Test
    void testFlightDetailsDTOCreation() {
        assertNotNull(flightDetailsDTO);
        assertEquals(1, flightDetailsDTO.getId());
        assertEquals(LocalDateTime.of(2024, 12, 15, 10, 30), flightDetailsDTO.getDeparture());
        assertEquals(LocalDateTime.of(2024, 12, 15, 13, 30), flightDetailsDTO.getArrival());
        assertEquals(originGate, flightDetailsDTO.getOriginGate());
        assertEquals(destinationGate, flightDetailsDTO.getDestinationGate());
        assertEquals(aircraft, flightDetailsDTO.getAircraft());
        assertNotNull(flightDetailsDTO.getPassengerList());
        assertEquals(2, flightDetailsDTO.getPassengerList().size());
    }

    @Test
    void testSetAndGetPassengers() {
        Passenger passenger3 = new Passenger();
        passenger3.setId(3);
        passenger3.setFirstName("Alice");
        passenger3.setLastName("Johnson");

        passengers.add(passenger3);
        flightDetailsDTO.setPassengerList(passengers);

        assertEquals(3, flightDetailsDTO.getPassengerList().size());
        assertTrue(flightDetailsDTO.getPassengerList().contains(passenger3));
    }

    @Test
    void testSetAndGetOriginGate() {
        City city = new City("San Francisco", "CA", 900000);
        AirportSingleDTO newOriginAirport = new AirportSingleDTO(3, "SFO", "SFO Code", city);
        GateFlightDTO newOriginGate = new GateFlightDTO(3, "C1", newOriginAirport);
        flightDetailsDTO.setOriginGate(newOriginGate);

        assertEquals(newOriginGate, flightDetailsDTO.getOriginGate());
    }

    @Test
    void testSetAndGetDestinationGate() {
        City city = new City("San Diego", "CA", 1400000);
        AirportSingleDTO newDestinationAirport = new AirportSingleDTO(4, "SAN", "SAN Code", city);
        GateFlightDTO newDestinationGate = new GateFlightDTO(4, "D1", newDestinationAirport);
        flightDetailsDTO.setDestinationGate(newDestinationGate);

        assertEquals(newDestinationGate, flightDetailsDTO.getDestinationGate());
    }

    @Test
    void testSetAndGetAircraft() {
        Aircraft newAircraft = new Aircraft();
        newAircraft.setId(2);
        newAircraft.setType("Airbus A320");
        newAircraft.setPassengerCapacity(200);

        flightDetailsDTO.setAircraft(newAircraft);

        assertEquals(newAircraft, flightDetailsDTO.getAircraft());
    }

    @Test
    void testSetAndGetTimes() {
        LocalDateTime newDeparture = LocalDateTime.of(2024, 12, 15, 12, 0);
        LocalDateTime newArrival = LocalDateTime.of(2024, 12, 15, 15, 0);

        flightDetailsDTO.setDeparture(newDeparture);
        flightDetailsDTO.setArrival(newArrival);

        assertEquals(newDeparture, flightDetailsDTO.getDeparture());
        assertEquals(newArrival, flightDetailsDTO.getArrival());
    }
}