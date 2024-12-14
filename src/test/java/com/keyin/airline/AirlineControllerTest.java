package com.keyin.airline;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AirlineController.class)
class AirlineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirlineService airlineService;

    @Autowired
    private ObjectMapper objectMapper;

    private Airline testAirline;

    @BeforeEach
    void setUp() {
        testAirline = new Airline("Test Airline", "Test Country");
        testAirline.setId(1);
    }

    @Test
    void testGetAllAirlines() throws Exception {
        AirlineTableDTO airlineDTO = new AirlineTableDTO(1, "Test Airline", "Test Country", 5);

        Mockito.when(airlineService.getAllAirlinesForTable())
                .thenReturn(Arrays.asList(airlineDTO));

        mockMvc.perform(get("/airline/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test Airline")))
                .andExpect(jsonPath("$[0].country", is("Test Country")))
                .andExpect(jsonPath("$[0].aircraft", is(5)));
    }

    @Test
    void testGetAirlineById() throws Exception {
        Mockito.when(airlineService.getAirlineById(1)).thenReturn(testAirline);

        mockMvc.perform(get("/airline/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test Airline")))
                .andExpect(jsonPath("$.country", is("Test Country")));
    }

    @Test
    void testGetAirlineByName() throws Exception {
        Mockito.when(airlineService.getAirlineByName("Test Airline")).thenReturn(testAirline);

        mockMvc.perform(get("/airline/name/Test Airline"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test Airline")))
                .andExpect(jsonPath("$.country", is("Test Country")));
    }

    @Test
    void testAddAirline() throws Exception {
        Mockito.when(airlineService.addAirline(any(Airline.class))).thenReturn(testAirline);

        mockMvc.perform(post("/airline")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testAirline)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test Airline")))
                .andExpect(jsonPath("$.country", is("Test Country")));
    }

    @Test
    void testUpdateAirlineById() throws Exception {
        Airline updatedAirline = new Airline("Updated Airline", "Updated Country");
        updatedAirline.setId(1);

        Mockito.when(airlineService.updateAirlineById(eq(1), any(Airline.class))).thenReturn(updatedAirline);

        mockMvc.perform(patch("/airline/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedAirline)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Airline")))
                .andExpect(jsonPath("$.country", is("Updated Country")));
    }

    @Test
    void testDeleteAirlineById() throws Exception {
        Mockito.doNothing().when(airlineService).deleteAirlineById(1);

        mockMvc.perform(delete("/airline/id/1"))
                .andExpect(status().isOk());
    }
}