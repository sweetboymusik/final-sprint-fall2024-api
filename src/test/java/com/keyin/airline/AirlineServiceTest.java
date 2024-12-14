package com.keyin.airline;

import com.keyin.aircraft.Aircraft;
import com.keyin.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirlineServiceTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineService airlineService;

    private Airline testAirline;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testAirline = new Airline("Test Airline", "Test Country");
        testAirline.setId(1);
        testAirline.setAircraftList(Arrays.asList(new Aircraft(), new Aircraft()));
    }

    @Test
    void testGetAllAirlines() {
        when(airlineRepository.findAll()).thenReturn(List.of(testAirline));

        Iterable<Airline> airlines = airlineService.getAllAirlines();
        assertNotNull(airlines);
        assertTrue(airlines.iterator().hasNext());

        verify(airlineRepository, times(1)).findAll();
    }

    @Test
    void testGetAllAirlinesForTable() {
        when(airlineRepository.findAll()).thenReturn(List.of(testAirline));

        Iterable<AirlineTableDTO> airlineDTOs = airlineService.getAllAirlinesForTable();
        assertNotNull(airlineDTOs);

        AirlineTableDTO dto = airlineDTOs.iterator().next();
        assertEquals(testAirline.getId(), dto.getId());
        assertEquals(testAirline.getName(), dto.getName());
        assertEquals(testAirline.getCountry(), dto.getCountry());
        assertEquals(testAirline.getAircraftList().size(), dto.getAircraft());

        verify(airlineRepository, times(1)).findAll();
    }

    @Test
    void testAddAirline() {
        when(airlineRepository.save(testAirline)).thenReturn(testAirline);

        Airline savedAirline = airlineService.addAirline(testAirline);
        assertNotNull(savedAirline);
        assertEquals(testAirline.getName(), savedAirline.getName());
        assertEquals(testAirline.getCountry(), savedAirline.getCountry());

        verify(airlineRepository, times(1)).save(testAirline);
    }

    @Test
    void testGetAirlineById() {
        when(airlineRepository.findById(testAirline.getId())).thenReturn(Optional.of(testAirline));

        Airline foundAirline = airlineService.getAirlineById(testAirline.getId());
        assertNotNull(foundAirline);
        assertEquals(testAirline.getName(), foundAirline.getName());

        verify(airlineRepository, times(1)).findById(testAirline.getId());
    }

    @Test
    void testGetAirlineByIdNotFound() {
        when(airlineRepository.findById(testAirline.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> airlineService.getAirlineById(testAirline.getId()));

        verify(airlineRepository, times(1)).findById(testAirline.getId());
    }

    @Test
    void testGetAirlineByName() {
        when(airlineRepository.findByName(testAirline.getName())).thenReturn(testAirline);

        Airline foundAirline = airlineService.getAirlineByName(testAirline.getName());
        assertNotNull(foundAirline);
        assertEquals(testAirline.getName(), foundAirline.getName());

        verify(airlineRepository, times(1)).findByName(testAirline.getName());
    }

    @Test
    void testGetAirlineByNameNotFound() {
        when(airlineRepository.findByName(testAirline.getName())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> airlineService.getAirlineByName(testAirline.getName()));

        verify(airlineRepository, times(1)).findByName(testAirline.getName());
    }

    @Test
    void testUpdateAirlineById() {
        Airline updatedAirline = new Airline("Updated Airline", "Updated Country");
        when(airlineRepository.findById(testAirline.getId())).thenReturn(Optional.of(testAirline));
        when(airlineRepository.save(any(Airline.class))).thenReturn(updatedAirline);

        Airline result = airlineService.updateAirlineById(testAirline.getId(), updatedAirline);
        assertNotNull(result);
        assertEquals(updatedAirline.getName(), result.getName());
        assertEquals(updatedAirline.getCountry(), result.getCountry());

        verify(airlineRepository, times(1)).findById(testAirline.getId());
        verify(airlineRepository, times(1)).save(testAirline);
    }

    @Test
    void testDeleteAirlineById() {
        when(airlineRepository.findById(testAirline.getId())).thenReturn(Optional.of(testAirline));
        doNothing().when(airlineRepository).delete(testAirline);

        assertDoesNotThrow(() -> airlineService.deleteAirlineById(testAirline.getId()));

        verify(airlineRepository, times(1)).findById(testAirline.getId());
        verify(airlineRepository, times(1)).delete(testAirline);
    }
}