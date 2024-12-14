package com.keyin.city;

import com.keyin.airport.Airport;
import com.keyin.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CityServiceTest {
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCities() {
        City city1 = new City("City1", "State1", 100000);
        City city2 = new City("City2", "State2", 200000);
        when(cityRepository.findAll()).thenReturn(Arrays.asList(city1, city2));

        Iterable<City> result = cityService.getAllCities();

        verify(cityRepository, times(1)).findAll();
        assertNotNull(result);
        assertEquals(2, ((List<City>) result).size());
    }

    @Test
    void testGetAllCitiesForTable() {
        City city1 = new City("City1", "State1", 100000);
        City city2 = new City("City2", "State2", 200000);

        city1.setAirports(Collections.singletonList(new Airport()));
        city2.setAirports(Arrays.asList(new Airport(), new Airport()));

        when(cityRepository.findAll()).thenReturn(Arrays.asList(city1, city2));

        Iterable<CityTableDTO> result = cityService.getAllCitiesForTable();

        verify(cityRepository, times(1)).findAll();
        assertNotNull(result);
        List<CityTableDTO> dtoList = (List<CityTableDTO>) result;
        assertEquals(2, dtoList.size());
        assertEquals(1, dtoList.get(0).getAirports());
        assertEquals(2, dtoList.get(1).getAirports());
    }

    @Test
    void testAddCity() {
        City city = new City("City1", "State1", 100000);
        when(cityRepository.save(city)).thenReturn(city);

        City result = cityService.addCity(city);

        verify(cityRepository, times(1)).save(city);
        assertNotNull(result);
        assertEquals("City1", result.getName());
    }

    @Test
    void testGetCityById_Success() {
        City city = new City("City1", "State1", 100000);
        when(cityRepository.findById(1)).thenReturn(Optional.of(city));

        City result = cityService.getCityById(1);

        verify(cityRepository, times(1)).findById(1);
        assertNotNull(result);
        assertEquals("City1", result.getName());
    }

    @Test
    void testGetCityById_NotFound() {
        when(cityRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> cityService.getCityById(1));
        verify(cityRepository, times(1)).findById(1);
    }

    @Test
    void testGetCityByName_Success() {
        City city = new City("City1", "State1", 100000);
        when(cityRepository.findByName("City1")).thenReturn(city);

        City result = cityService.getCityByName("City1");

        verify(cityRepository, times(1)).findByName("City1");
        assertNotNull(result);
        assertEquals("City1", result.getName());
    }

    @Test
    void testGetCityByName_NotFound() {
        when(cityRepository.findByName("City1")).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> cityService.getCityByName("City1"));
        verify(cityRepository, times(1)).findByName("City1");
    }

    @Test
    void testUpdateCityById() {
        City cityToUpdate = new City("City1", "State1", 100000);
        when(cityRepository.findById(1)).thenReturn(Optional.of(cityToUpdate));
        when(cityRepository.save(any(City.class))).thenReturn(cityToUpdate);

        City updatedCity = new City("UpdatedCity", "UpdatedState", 200000);
        City result = cityService.updateCityById(1, updatedCity);

        verify(cityRepository, times(1)).findById(1);
        verify(cityRepository, times(1)).save(cityToUpdate);
        assertEquals("UpdatedCity", result.getName());
        assertEquals("UpdatedState", result.getState());
        assertEquals(200000, result.getPopulation());
    }

    @Test
    void testDeleteCityById() {
        City city = new City("City1", "State1", 100000);
        when(cityRepository.findById(1)).thenReturn(Optional.of(city));

        cityService.deleteCityById(1);

        verify(cityRepository, times(1)).findById(1);
        verify(cityRepository, times(1)).delete(city);
    }
}