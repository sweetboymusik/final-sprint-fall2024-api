package com.keyin.passenger;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/passenger/all")
    public Iterable<PassengerTableDTO> getAllPassengersForTable() {
        return passengerService.getAllPassengersForTable();
    }

    @GetMapping("passenger/id/{id}")
    @JsonView(Views.PassengerView.class)
    public PassengerDetailsDTO getPassengerDetailsById(@PathVariable int id) {
        return passengerService.getPassengerDetailsById(id);
    }

    @GetMapping("passenger/name/{name}")
    @JsonView(Views.PassengerView.class)
    public Passenger getPassengerByName(@PathVariable String name) {
        return passengerService.getPassengerByName(name);
    }

    @PostMapping("/passenger")
    @JsonView(Views.PassengerView.class)
    public Passenger addPassenger(@RequestBody PassengerDTO passengerDTO) {
        return passengerService.addPassenger(passengerDTO);
    }

    @PatchMapping("/passenger/id/{id}")
    @JsonView(Views.PassengerView.class)
    public Passenger updatePassengerById(@PathVariable int id, @RequestBody PassengerDTO passengerDTO) {
        return passengerService.updatePassengerById(id, passengerDTO);
    }

    @DeleteMapping("/passenger/id/{id}")
    @JsonView(Views.PassengerView.class)
    public void deletePassengerById(@PathVariable int id) {
        passengerService.deletePassengerById(id);
    }
}
