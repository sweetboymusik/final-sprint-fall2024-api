package com.keyin.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CityControllerTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllCities() throws Exception {
        CityTableDTO city1 = new CityTableDTO(1, "City1", "State1", 100000, 2);
        CityTableDTO city2 = new CityTableDTO(2, "City2", "State2", 200000, 3);

        when(cityService.getAllCitiesForTable()).thenReturn(Arrays.asList(city1, city2));

        mockMvc.perform(get("/city/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("City1"))
                .andExpect(jsonPath("$[1].name").value("City2"));

        verify(cityService, times(1)).getAllCitiesForTable();
    }

    @Test
    void testGetCityById() throws Exception {
        City city = new City("City1", "State1", 100000);
        city.setId(1);

        when(cityService.getCityById(1)).thenReturn(city);

        mockMvc.perform(get("/city/id/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("City1"));

        verify(cityService, times(1)).getCityById(1);
    }

    @Test
    void testGetCityByName() throws Exception {
        City city = new City("City1", "State1", 100000);
        city.setId(1);

        when(cityService.getCityByName("City1")).thenReturn(city);

        mockMvc.perform(get("/city/name/{name}", "City1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("City1"))
                .andExpect(jsonPath("$.state").value("State1"));

        verify(cityService, times(1)).getCityByName("City1");
    }

    @Test
    void testAddCity() throws Exception {
        City city = new City("City1", "State1", 100000);

        when(cityService.addCity(any(City.class))).thenReturn(city);

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(city)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("City1"))
                .andExpect(jsonPath("$.state").value("State1"));

        verify(cityService, times(1)).addCity(any(City.class));
    }

    @Test
    void testUpdateCityById() throws Exception {
        City updatedCity = new City("UpdatedCity", "UpdatedState", 200000);
        updatedCity.setId(1);

        when(cityService.updateCityById(eq(1), any(City.class))).thenReturn(updatedCity);

        mockMvc.perform(patch("/city/id/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedCity"))
                .andExpect(jsonPath("$.state").value("UpdatedState"));

        verify(cityService, times(1)).updateCityById(eq(1), any(City.class));
    }

    @Test
    void testDeleteCityById() throws Exception {
        doNothing().when(cityService).deleteCityById(1);

        mockMvc.perform(delete("/city/id/{id}", 1))
                .andExpect(status().isOk());

        verify(cityService, times(1)).deleteCityById(1);
    }
}