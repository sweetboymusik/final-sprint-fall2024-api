package com.keyin.airport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.gate.Gate;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Autowired
    private ObjectMapper objectMapper;

    private AirportDTO airportDTO;

    @BeforeEach
    void setUp() {
        airportDTO = new AirportDTO("Test Airport", "TST", 1, Collections.emptyList());
    }

    @Test
    void testGetAllAirportsForTable() throws Exception {
        AirportTableDTO airportTableDTO = new AirportTableDTO(1, "Test Airport", "TST", null, 5);
        when(airportService.getAllAirportsForTable()).thenReturn(Collections.singletonList(airportTableDTO));

        mockMvc.perform(get("/airport/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Test Airport"));

        verify(airportService, times(1)).getAllAirportsForTable();
    }

    @Test
    void testGetAirportById() throws Exception {
        AirportSingleDTO airportSingleDTO = new AirportSingleDTO(1, "Test Airport", "TST", null);
        when(airportService.getSingleAirportById(1)).thenReturn(airportSingleDTO);

        mockMvc.perform(get("/airport/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Airport"))
                .andExpect(jsonPath("$.code").value("TST"));

        verify(airportService, times(1)).getSingleAirportById(1);
    }

    @Test
    void testGetGatesByAirportId() throws Exception {
        Gate gate = new Gate();
        gate.setId(1);
        gate.setGateNumber("A1");
        when(airportService.getGatesByAirportId(1)).thenReturn(Collections.singletonList(gate));

        mockMvc.perform(get("/airport/1/gates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].gateNumber").value("A1"));

        verify(airportService, times(1)).getGatesByAirportId(1);
    }

    @Test
    void testAddAirport() throws Exception {
        Airport airport = new Airport("Test Airport", "TST", null);
        when(airportService.addAirport(Mockito.any(AirportDTO.class))).thenReturn(airport);

        mockMvc.perform(post("/airport")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Airport"))
                .andExpect(jsonPath("$.code").value("TST"));

        verify(airportService, times(1)).addAirport(Mockito.any(AirportDTO.class));
    }

    @Test
    void testAddGateToAirport() throws Exception {
        Gate gate = new Gate();
        gate.setId(1);
        gate.setGateNumber("A1");
        when(airportService.addGateToAirport(eq(1), Mockito.any(Gate.class))).thenReturn(gate);

        mockMvc.perform(post("/airport/1/gates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gateNumber").value("A1"));

        verify(airportService, times(1)).addGateToAirport(eq(1), Mockito.any(Gate.class));
    }

    @Test
    void testUpdateAirportById() throws Exception {
        Airport airport = new Airport("Updated Airport", "UPD", null);
        when(airportService.updateAirportById(eq(1), Mockito.any(AirportDTO.class))).thenReturn(airport);

        mockMvc.perform(patch("/airport/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airportDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Airport"))
                .andExpect(jsonPath("$.code").value("UPD"));

        verify(airportService, times(1)).updateAirportById(eq(1), Mockito.any(AirportDTO.class));
    }

    @Test
    void testDeleteAirportById() throws Exception {
        doNothing().when(airportService).deleteAirportById(1);

        mockMvc.perform(delete("/airport/id/1"))
                .andExpect(status().isOk());

        verify(airportService, times(1)).deleteAirportById(1);
    }
}