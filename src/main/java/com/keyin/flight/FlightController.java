package com.keyin.flight;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/flight/all")
    @JsonView(Views.FlightView.class)
    public Iterable<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/flight/id/{id}")
    @JsonView(Views.FlightView.class)
    public Flight getFlightById(@PathVariable int id) {
        return flightService.getFlightById(id);
    }

    @PostMapping("/flight")
    @JsonView(Views.FlightView.class)
    public Flight addFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.addFlight(flightDTO);
    }

    @PostMapping("/flight/id/{flightId}/passengers/add/{passengerId}")
    @JsonView(Views.FlightView.class)
    public String addPassengerById(@PathVariable int flightId, @PathVariable int passengerId) {
        return flightService.addPassengerById(flightId, passengerId);

    }

    @PostMapping("/flight/id/{id}")
    @JsonView(Views.FlightView.class)
    public Flight updateFlightById(@PathVariable int id, @RequestBody FlightDTO flightDTO) {
        return flightService.updateFlightById(id, flightDTO);
    }

    @DeleteMapping("/flight/id/{id}")
    public void deleteFlightById(@PathVariable int id) {
        flightService.deleteFlightById(id);
    }
}
