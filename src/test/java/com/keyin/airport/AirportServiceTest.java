package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.city.CityService;
import com.keyin.exceptions.EntityNotFoundException;
import com.keyin.gate.GateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirportServiceTest {

    @InjectMocks
    private AirportService airportService;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private CityService cityService;

    @Mock
    private GateRepository gateRepository;

    public AirportServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAirports() {
        List<Airport> airports = List.of(
                new Airport("Airport1", "A1", new City("City1", "State1", 100000)),
                new Airport("Airport2", "A2", new City("City2", "State2", 200000)));
        when(airportRepository.findAll()).thenReturn(airports);

        Iterable<Airport> result = airportService.getAllAirports();

        assertNotNull(result);
        assertEquals(2, ((List<Airport>) result).size());
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void testAddAirport() {
        City city = new City("City1", "State1", 100000);
        when(cityService.getCityById(1)).thenReturn(city);
        AirportDTO airportDTO = new AirportDTO("Airport1", "A1", 1, null);
        Airport airport = new Airport(airportDTO, city);

        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        Airport result = airportService.addAirport(airportDTO);

        assertNotNull(result);
        assertEquals("Airport1", result.getName());
        verify(cityService, times(1)).getCityById(1);
        verify(airportRepository, times(1)).save(any(Airport.class));
    }

    @Test
    void testGetAirportById_Success() {
        Airport airport = new Airport("Airport1", "A1", new City("City1", "State1", 100000));
        when(airportRepository.findById(1)).thenReturn(Optional.of(airport));

        Airport result = airportService.getAirportById(1);

        assertNotNull(result);
        assertEquals("Airport1", result.getName());
        verify(airportRepository, times(1)).findById(1);
    }

    @Test
    void testGetAirportById_NotFound() {
        when(airportRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> airportService.getAirportById(1));
        verify(airportRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateAirportById() {
        Airport existingAirport = new Airport("Old Airport", "OLD", new City("City1", "State1", 100000));
        AirportDTO airportDTO = new AirportDTO("New Airport", "NEW", 1, null);
        City city = new City("City1", "State1", 100000);

        when(airportRepository.findById(1)).thenReturn(Optional.of(existingAirport));
        when(cityService.getCityById(1)).thenReturn(city);
        when(airportRepository.save(any(Airport.class))).thenReturn(existingAirport);

        Airport result = airportService.updateAirportById(1, airportDTO);

        assertNotNull(result);
        assertEquals("New Airport", result.getName());
        assertEquals("NEW", result.getCode());
        verify(airportRepository, times(1)).findById(1);
        verify(cityService, times(1)).getCityById(1);
        verify(airportRepository, times(1)).save(any(Airport.class));
    }

    @Test
    void testDeleteAirportById() {
        Airport airport = new Airport("Airport1", "A1", new City("City1", "State1", 100000));
        when(airportRepository.findById(1)).thenReturn(Optional.of(airport));

        airportService.deleteAirportById(1);

        verify(airportRepository, times(1)).findById(1);
        verify(airportRepository, times(1)).delete(airport);
    }
}