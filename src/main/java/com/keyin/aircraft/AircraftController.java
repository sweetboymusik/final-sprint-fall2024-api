package com.keyin.aircraft;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/aircraft/all")
    public Iterable<AircraftTableDTO> getAllAircraftForTable() {
        return aircraftService.getAllAircraftForTable();
    }

    @GetMapping("/aircraft/id/{id}")
    @JsonView(Views.AircraftView.class)
    public Aircraft getAircraftById(@PathVariable int id) {
        return aircraftService.getAircraftById(id);
    }

    @PostMapping("/aircraft")
    @JsonView(Views.AircraftView.class)
    public Aircraft addAircraft(@RequestBody AircraftDTO aircraftDTO) {
        return aircraftService.addAircraft(aircraftDTO);
    }

    @PatchMapping("/aircraft/id/{id}")
    @JsonView(Views.AircraftView.class)
    public Aircraft updateAircraftById(@PathVariable int id, @RequestBody AircraftDTO aircraftDTO) {
        return aircraftService.updateAircraftById(id, aircraftDTO);
    }

    @DeleteMapping("/aircraft/id/{id}")
    @JsonView(Views.AircraftView.class)
    public void deleteAircraftById(@PathVariable int id) {
        aircraftService.deleteAircraftById(id);
    }
}
