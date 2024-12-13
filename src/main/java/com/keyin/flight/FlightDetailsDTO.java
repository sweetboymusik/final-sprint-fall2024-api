package com.keyin.flight;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.aircraft.Aircraft;
import com.keyin.gate.GateFlightDTO;
import com.keyin.passenger.Passenger;
import com.keyin.views.Views;

public class FlightDetailsDTO {
    // instance variables
    @JsonView({ Views.FlightView.class })
    private int id;
    @JsonView({ Views.FlightView.class })
    private LocalDateTime departure;
    @JsonView({ Views.FlightView.class })
    private LocalDateTime arrival;
    @JsonView({ Views.FlightView.class })
    private GateFlightDTO originGate;
    @JsonView({ Views.FlightView.class })
    private GateFlightDTO destinationGate;
    @JsonView({ Views.FlightView.class })
    private List<Passenger> passengerList;
    @JsonView({ Views.FlightView.class })
    private Aircraft aircraft;

    // constructors
    public FlightDetailsDTO(int id, LocalDateTime departure, LocalDateTime arrival, GateFlightDTO originGate,
            GateFlightDTO destinationGate, List<Passenger> passengers, Aircraft aircraft) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.originGate = originGate;
        this.destinationGate = destinationGate;
        this.passengerList = passengers;
        this.aircraft = aircraft;
    }

    // getters and setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDeparture() {
        return this.departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return this.arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public GateFlightDTO getOriginGate() {
        return this.originGate;
    }

    public void setOriginGate(GateFlightDTO originGate) {
        this.originGate = originGate;
    }

    public GateFlightDTO getDestinationGate() {
        return this.destinationGate;
    }

    public void setDestinationGate(GateFlightDTO destinationGate) {
        this.destinationGate = destinationGate;
    }

    public List<Passenger> getPassengerList() {
        return this.passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public Aircraft getAircraft() {
        return this.aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }
}