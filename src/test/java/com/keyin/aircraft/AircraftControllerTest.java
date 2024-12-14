package com.keyin.aircraft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.airline.Airline;
import com.keyin.airline.AirlineService;
import com.keyin.airline.AirlineFormattedDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AircraftController.class)
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AircraftService aircraftService;

    @MockBean
    private AirlineService airlineService;

    @Autowired
    private ObjectMapper objectMapper;

    private Aircraft testAircraft;
    private AircraftDTO testAircraftDTO;
    private Airline testAirline;

    @BeforeEach
    void setUp() {
        testAirline = new Airline("Air Canada", "Canada");
        testAircraft = new Aircraft("Boeing 737", 200, testAirline);
        testAircraft.setId(1);

        testAircraftDTO = new AircraftDTO("Airbus A320", 180, 1);
    }

    @Test
    void testGetAllAircraftForTable() throws Exception {
        AircraftTableDTO aircraftTableDTO = new AircraftTableDTO(
                testAircraft.getId(), testAircraft.getType(),
                testAircraft.getPassengerCapacity(),
                new AirlineFormattedDTO(testAirline.getName()));

        Mockito.when(aircraftService.getAllAircraftForTable())
                .thenReturn(Collections.singletonList(aircraftTableDTO));

        mockMvc.perform(get("/aircraft/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(testAircraft.getId())))
                .andExpect(jsonPath("$[0].type", is(testAircraft.getType())))
                .andExpect(jsonPath("$[0].passengerCapacity", is(testAircraft.getPassengerCapacity())))
                .andExpect(jsonPath("$[0].airline.name", is(testAirline.getName())));
    }

    @Test
    void testGetAircraftById() throws Exception {
        Mockito.when(aircraftService.getAircraftById(1)).thenReturn(testAircraft);

        mockMvc.perform(get("/aircraft/id/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testAircraft.getId())))
                .andExpect(jsonPath("$.type", is(testAircraft.getType())))
                .andExpect(jsonPath("$.passengerCapacity", is(testAircraft.getPassengerCapacity())))
                .andExpect(jsonPath("$.airline.name", is(testAirline.getName())));
    }

    @Test
    void testAddAircraft() throws Exception {
        Mockito.when(aircraftService.addAircraft(any(AircraftDTO.class))).thenReturn(testAircraft);

        mockMvc.perform(post("/aircraft")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testAircraftDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testAircraft.getId())))
                .andExpect(jsonPath("$.type", is(testAircraft.getType())))
                .andExpect(jsonPath("$.passengerCapacity", is(testAircraft.getPassengerCapacity())))
                .andExpect(jsonPath("$.airline.name", is(testAirline.getName())));
    }

    @Test
    void testUpdateAircraftById() throws Exception {
        Mockito.when(aircraftService.updateAircraftById(Mockito.eq(1), any(AircraftDTO.class)))
                .thenReturn(testAircraft);

        mockMvc.perform(patch("/aircraft/id/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testAircraftDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testAircraft.getId())))
                .andExpect(jsonPath("$.type", is(testAircraft.getType())))
                .andExpect(jsonPath("$.passengerCapacity", is(testAircraft.getPassengerCapacity())))
                .andExpect(jsonPath("$.airline.name", is(testAirline.getName())));
    }

    @Test
    void testDeleteAircraftById() throws Exception {
        Mockito.doNothing().when(aircraftService).deleteAircraftById(1);

        mockMvc.perform(delete("/aircraft/id/{id}", 1))
                .andExpect(status().isOk());

        Mockito.verify(aircraftService, Mockito.times(1)).deleteAircraftById(1);
    }
}