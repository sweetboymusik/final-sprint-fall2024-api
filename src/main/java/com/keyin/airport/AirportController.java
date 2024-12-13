package com.keyin.airport;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.gate.Gate;
import com.keyin.views.Views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/airport/all")
    public Iterable<AirportTableDTO> getAllAirportsForTable() {
        return airportService.getAllAirportsForTable();
    }

    @GetMapping("/airport/id/{id}")
    @JsonView(Views.AirportView.class)
    public AirportSingleDTO getAirportById(@PathVariable int id) {
        return airportService.getSingleAirportById(id);
    }

    @GetMapping("/airport/name/{name}")
    @JsonView(Views.AirportView.class)
    public Airport getAirportByName(@PathVariable String name) {
        return airportService.getAirportByName(name);
    }

    @GetMapping("/airport/{id}/gates")
    @JsonView(Views.AirportView.class)
    public List<Gate> getGatesByAirportId(@PathVariable int id) {
        return airportService.getGatesByAirportId(id);
    }

    @PostMapping("/airport")
    @JsonView(Views.AirportView.class)
    public Airport addAirport(@RequestBody AirportDTO airportDTO) {
        return airportService.addAirport(airportDTO);
    }

    @PostMapping("/airport/{id}/gates")
    public Gate addGateToAirport(@PathVariable int id, @RequestBody Gate gate) {
        return airportService.addGateToAirport(id, gate);
    }

    @PatchMapping("/airport/id/{id}")
    @JsonView(Views.AirportView.class)
    public Airport updateAirportById(@PathVariable int id, @RequestBody AirportDTO airportDTO) {
        return airportService.updateAirportById(id, airportDTO);
    }

    @DeleteMapping("/airport/id/{id}")
    @JsonView(Views.AirportView.class)
    public void deleteAirportById(@PathVariable int id) {
        airportService.deleteAirportById(id);
    }
}
