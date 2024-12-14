package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.city.CityService;
import com.keyin.exceptions.EntityNotFoundException;
import com.keyin.gate.Gate;
import com.keyin.gate.GateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirportServiceTest {

    private AirportRepository airportRepository;
    private CityService cityService;
    private GateRepository gateRepository;
    private AirportService airportService;

    @BeforeEach
    void setUp() {
        airportRepository = mock(AirportRepository.class);
        cityService = mock(CityService.class);
        gateRepository = mock(GateRepository.class);
        airportService = new AirportService(airportRepository, cityService, gateRepository);
    }

    @Test
    void testGetAllAirports() {
        Airport airport = new Airport("Test Airport", "TST", null);
        when(airportRepository.findAll()).thenReturn(Collections.singletonList(airport));

        Iterable<Airport> airports = airportService.getAllAirports();

        assertNotNull(airports);
        assertTrue(airports.iterator().hasNext());
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void testAddAirport() {
        City city = new City("Test City", "TS", 100000);
        city.setId(1);

        AirportDTO airportDTO = new AirportDTO("Test Airport", "TST", 1, null);
        Airport airport = new Airport(airportDTO, city);

        when(cityService.getCityById(1)).thenReturn(city);
        when(airportRepository.save(Mockito.any(Airport.class))).thenReturn(airport);

        Airport savedAirport = airportService.addAirport(airportDTO);

        assertNotNull(savedAirport);
        assertEquals("Test Airport", savedAirport.getName());
        assertEquals("TST", savedAirport.getCode());
        verify(cityService, times(1)).getCityById(1);
        verify(airportRepository, times(1)).save(Mockito.any(Airport.class));
    }

    @Test
    void testAddGateToAirport() {
        Airport airport = new Airport("Test Airport", "TST", null);
        airport.setId(1);

        Gate gate = new Gate();
        gate.setGateNumber("A1");

        when(airportRepository.findById(1)).thenReturn(Optional.of(airport));
        when(gateRepository.save(Mockito.any(Gate.class))).thenReturn(gate);

        Gate savedGate = airportService.addGateToAirport(1, gate);

        assertNotNull(savedGate);
        assertEquals("A1", savedGate.getGateNumber());
        verify(airportRepository, times(1)).findById(1);
        verify(gateRepository, times(1)).save(Mockito.any(Gate.class));
    }

    @Test
    void testGetAirportById() {
        Airport airport = new Airport("Test Airport", "TST", null);
        when(airportRepository.findById(1)).thenReturn(Optional.of(airport));

        Airport result = airportService.getAirportById(1);

        assertNotNull(result);
        assertEquals("Test Airport", result.getName());
        verify(airportRepository, times(1)).findById(1);
    }

    @Test
    void testGetAirportById_NotFound() {
        when(airportRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> airportService.getAirportById(1));
        assertEquals("Airport not found", exception.getMessage());
        verify(airportRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateAirportById() {
        City city = new City("Test City", "TS", 100000);
        city.setId(1);

        Airport existingAirport = new Airport("Old Airport", "OLD", city);
        existingAirport.setId(1);

        AirportDTO updateDTO = new AirportDTO("Updated Airport", "UPD", 1, null);

        when(airportRepository.findById(1)).thenReturn(Optional.of(existingAirport));
        when(cityService.getCityById(1)).thenReturn(city);
        when(airportRepository.save(Mockito.any(Airport.class))).thenReturn(existingAirport);

        Airport updatedAirport = airportService.updateAirportById(1, updateDTO);

        assertNotNull(updatedAirport);
        assertEquals("Updated Airport", updatedAirport.getName());
        assertEquals("UPD", updatedAirport.getCode());
        verify(airportRepository, times(1)).findById(1);
        verify(cityService, times(1)).getCityById(1);
        verify(airportRepository, times(1)).save(Mockito.any(Airport.class));
    }

    @Test
    void testDeleteAirportById() {
        Airport airport = new Airport("Test Airport", "TST", null);
        airport.setId(1);

        when(airportRepository.findById(1)).thenReturn(Optional.of(airport));
        doNothing().when(airportRepository).delete(airport);

        airportService.deleteAirportById(1);

        verify(airportRepository, times(1)).findById(1);
        verify(airportRepository, times(1)).delete(airport);
    }

    @Test
    void testGetGatesByAirportId() {
        Airport airport = new Airport("Test Airport", "TST", null);
        airport.setId(1);

        Gate gate = new Gate();
        gate.setId(1);
        gate.setGateNumber("A1");

        when(airportRepository.findById(1)).thenReturn(Optional.of(airport));
        when(gateRepository.findByAirport(airport)).thenReturn(Collections.singletonList(gate));

        List<Gate> gates = airportService.getGatesByAirportId(1);

        assertNotNull(gates);
        assertEquals(1, gates.size());
        assertEquals("A1", gates.get(0).getGateNumber());
        verify(airportRepository, times(1)).findById(1);
        verify(gateRepository, times(1)).findByAirport(airport);
    }
}