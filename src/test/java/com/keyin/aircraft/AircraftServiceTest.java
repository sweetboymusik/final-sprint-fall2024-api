package com.keyin.aircraft;

import com.keyin.airline.Airline;
import com.keyin.airline.AirlineService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private AirlineService airlineService;

    @InjectMocks
    private AircraftService aircraftService;

    private Airline testAirline;
    private Aircraft testAircraft;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testAirline = new Airline("Air Canada", "Canada");
        testAircraft = new Aircraft("Boeing 737", 189, testAirline);
        testAircraft.setId(1);
    }

    @Test
    void testGetAllAircraft() {
        when(aircraftRepository.findAll()).thenReturn(Arrays.asList(testAircraft));

        Iterable<Aircraft> aircrafts = aircraftService.getAllAircraft();

        assertNotNull(aircrafts);
        assertEquals(1, ((List<Aircraft>) aircrafts).size());
        verify(aircraftRepository, times(1)).findAll();
    }

    @Test
    void testGetAllAircraftForTable() {
        when(aircraftRepository.findAll()).thenReturn(Arrays.asList(testAircraft));

        Iterable<AircraftTableDTO> aircraftDTOs = aircraftService.getAllAircraftForTable();

        assertNotNull(aircraftDTOs);
        AircraftTableDTO dto = ((List<AircraftTableDTO>) aircraftDTOs).get(0);
        assertEquals("Boeing 737", dto.getType());
        assertEquals(189, dto.getPassengerCapacity());
        assertEquals("Air Canada", dto.getAirline().getName());
        verify(aircraftRepository, times(1)).findAll();
    }

    @Test
    void testAddAircraft() {
        AircraftDTO aircraftDTO = new AircraftDTO("Airbus A320", 180, 1);

        when(airlineService.getAirlineById(1)).thenReturn(testAirline);
        when(aircraftRepository.save(any(Aircraft.class))).thenReturn(testAircraft);

        Aircraft savedAircraft = aircraftService.addAircraft(aircraftDTO);

        assertNotNull(savedAircraft);
        assertEquals("Boeing 737", savedAircraft.getType());
        verify(airlineService, times(1)).getAirlineById(1);
        verify(aircraftRepository, times(1)).save(any(Aircraft.class));
    }

    @Test
    void testGetAircraftById() {
        when(aircraftRepository.findById(1)).thenReturn(Optional.of(testAircraft));

        Aircraft foundAircraft = aircraftService.getAircraftById(1);

        assertNotNull(foundAircraft);
        assertEquals("Boeing 737", foundAircraft.getType());
        verify(aircraftRepository, times(1)).findById(1);
    }

    @Test
    void testGetAircraftById_NotFound() {
        when(aircraftRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> aircraftService.getAircraftById(1));
        verify(aircraftRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateAircraftById() {
        AircraftDTO aircraftDTO = new AircraftDTO("Airbus A320", 180, 1);

        when(aircraftRepository.findById(1)).thenReturn(Optional.of(testAircraft));
        when(airlineService.getAirlineById(1)).thenReturn(testAirline);

        Aircraft updatedAircraft = new Aircraft("Airbus A320", 180, testAirline);
        updatedAircraft.setId(1);

        when(aircraftRepository.save(any(Aircraft.class))).thenAnswer(invocation -> {
            Aircraft aircraft = invocation.getArgument(0);
            aircraft.setId(1);
            return aircraft;
        });

        Aircraft result = aircraftService.updateAircraftById(1, aircraftDTO);

        assertNotNull(result);
        assertEquals("Airbus A320", result.getType());
        assertEquals(180, result.getPassengerCapacity());
        assertEquals("Air Canada", result.getAirline().getName());

        verify(aircraftRepository, times(1)).findById(1);
        verify(aircraftRepository, times(1)).save(any(Aircraft.class));
    }

    @Test
    void testDeleteAircraftById() {
        when(aircraftRepository.findById(1)).thenReturn(Optional.of(testAircraft));
        doNothing().when(aircraftRepository).delete(testAircraft);

        aircraftService.deleteAircraftById(1);

        verify(aircraftRepository, times(1)).findById(1);
        verify(aircraftRepository, times(1)).delete(testAircraft);
    }

    @Test
    void testDeleteAircraftById_NotFound() {
        when(aircraftRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> aircraftService.deleteAircraftById(1));
        verify(aircraftRepository, times(1)).findById(1);
    }
}