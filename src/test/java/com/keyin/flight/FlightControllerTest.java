package com.keyin.flight;

import com.keyin.aircraft.Aircraft;
import com.keyin.airport.AirportSingleDTO;
import com.keyin.gate.Gate;
import com.keyin.gate.GateFlightDTO;
import com.keyin.passenger.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    private MockMvc mockMvc;

    @SuppressWarnings("unused")
    private FlightDTO testFlightDTO;
    private Flight testFlight;
    @SuppressWarnings("unused")
    private Passenger testPassenger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();

        testFlightDTO = new FlightDTO(
                LocalDateTime.of(2024, 12, 25, 10, 0),
                LocalDateTime.of(2024, 12, 25, 14, 0),
                1, 2, 3, 150);

        testFlight = new Flight(
                LocalDateTime.of(2024, 12, 25, 10, 0),
                LocalDateTime.of(2024, 12, 25, 14, 0),
                new Gate("A1", null),
                new Gate("B2", null),
                new Aircraft("Boeing 737", 180, null),
                150);

        testPassenger = new Passenger("John", "Doe", "123-456-7890", "john.doe@example.com", null);
    }

    @Test
    void testGetAllFlightsForTable() throws Exception {
        when(flightService.getAllFlightsForTable()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/flight/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(flightService, times(1)).getAllFlightsForTable();
    }

    @Test
    void testGetFlightById() throws Exception {
        FlightDetailsDTO flightDetailsDTO = new FlightDetailsDTO(
                1,
                LocalDateTime.of(2024, 12, 25, 10, 0),
                LocalDateTime.of(2024, 12, 25, 14, 0),
                new GateFlightDTO(1, "Gate A", new AirportSingleDTO(1, "Airport A", "AAA", null)),
                new GateFlightDTO(2, "Gate B", new AirportSingleDTO(2, "Airport B", "BBB", null)),
                List.of(),
                new Aircraft("Boeing 737", 180, null));

        when(flightService.getFlightDetailsById(1)).thenReturn(flightDetailsDTO);

        mockMvc.perform(get("/flight/id/1"))
                .andExpect(status().isOk())
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print()) // Prints response
                .andReturn();
    }

    @Test
    void testAddFlight() throws Exception {
        when(flightService.addFlight(any(FlightDTO.class))).thenReturn(testFlight);

        mockMvc.perform(post("/flight")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"departure\":\"2024-12-25T10:00:00Z\",\"arrival\":\"2024-12-25T14:00:00Z\",\"originGateId\":1,\"destinationGateId\":2,\"aircraftId\":3,\"numberOfPassengers\":150}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departure").value("2024-12-25T10:00:00Z"))
                .andExpect(jsonPath("$.arrival").value("2024-12-25T14:00:00Z"));

        verify(flightService, times(1)).addFlight(any(FlightDTO.class));
    }

    @Test
    void testAddPassengerById() throws Exception {
        when(flightService.addPassengerById(1, 1)).thenReturn("Passenger added successfully");

        mockMvc.perform(post("/flight/id/1/passengers/add/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Passenger added successfully"));

        verify(flightService, times(1)).addPassengerById(1, 1);
    }

    @SuppressWarnings("unused")
    @Test
    void testUpdateFlightById() throws Exception {
        FlightDTO flightDTO = new FlightDTO(
                LocalDateTime.parse("2024-12-25T10:00:00Z", DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse("2024-12-25T14:00:00Z", DateTimeFormatter.ISO_DATE_TIME),
                1, 2, 3, 150);

        when(flightService.updateFlightById(eq(1), any(FlightDTO.class))).thenReturn(testFlight);

        mockMvc.perform(patch("/flight/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"departure\":\"2024-12-25T10:00:00Z\",\"arrival\":\"2024-12-25T14:00:00Z\",\"originGateId\":1,\"destinationGateId\":2,\"aircraftId\":3,\"numberOfPassengers\":150}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departure").value("2024-12-25T10:00:00Z"))
                .andExpect(jsonPath("$.arrival").value("2024-12-25T14:00:00Z"));

        verify(flightService, times(1)).updateFlightById(eq(1), any(FlightDTO.class));
    }

    @Test
    void testDeleteFlightById() throws Exception {
        mockMvc.perform(delete("/flight/id/1"))
                .andExpect(status().isOk());

        verify(flightService, times(1)).deleteFlightById(1);
    }
}