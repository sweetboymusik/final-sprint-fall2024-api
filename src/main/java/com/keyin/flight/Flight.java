package com.keyin.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.aircraft.Aircraft;
import com.keyin.gate.Gate;
import com.keyin.passenger.Passenger;
import com.keyin.views.Views;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight {
    // instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ Views.FlightView.class, Views.PassengerView.class, Views.AirportView.class })
    private int id;

    @JsonView({ Views.FlightView.class, Views.PassengerView.class, Views.AirportView.class })
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private LocalDateTime departure;

    @JsonView({ Views.FlightView.class, Views.PassengerView.class, Views.AirportView.class })
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private LocalDateTime arrival;

    @ManyToOne
    @JoinColumn(name = "origin_gate_id", nullable = true)
    @JsonView({ Views.FlightView.class, Views.PassengerView.class })
    private Gate originGate;

    @ManyToOne
    @JoinColumn(name = "destination_gate_id", nullable = true)
    @JsonView({ Views.FlightView.class, Views.PassengerView.class })
    private Gate destinationGate;

    @ManyToOne
    @JsonView({ Views.FlightView.class, Views.PassengerView.class })
    private Aircraft aircraft;

    @JsonView({})
    private int numberOfPassengers;

    @ManyToMany(mappedBy = "flights")
    @JsonView({ Views.FlightView.class })
    private List<Passenger> passengerList;

    // constructors
    public Flight() {
    }

    public Flight(LocalDateTime departure, LocalDateTime arrival, Gate originGate, Gate destinationGate,
            Aircraft aircraft, int numberOfPassengers) {
        this.departure = departure;
        this.arrival = arrival;
        this.originGate = originGate;
        this.destinationGate = destinationGate;
        this.aircraft = aircraft;
        this.numberOfPassengers = 0;
        this.passengerList = new ArrayList<>();
    }

    public Flight(FlightDTO flightDTO, Gate originGate, Gate destinationGate, Aircraft aircraft) {
        this.departure = flightDTO.getDeparture();
        this.arrival = flightDTO.getArrival();
        this.originGate = originGate;
        this.destinationGate = destinationGate;
        this.aircraft = aircraft;
        this.numberOfPassengers = 0;
        this.passengerList = new ArrayList<>();
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public Gate getOriginGate() {
        return originGate;
    }

    public void setOriginGate(Gate originGate) {
        this.originGate = originGate;
    }

    public Gate getDestinationGate() {
        return destinationGate;
    }

    public void setDestinationGate(Gate destinationGate) {
        this.destinationGate = destinationGate;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengers) {
        this.passengerList = passengers;
    }
}