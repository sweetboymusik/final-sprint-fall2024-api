package com.keyin.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import com.keyin.passenger.Passenger;
import com.keyin.views.Views;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.FlightView.class, Views.PassengerView.class})
    private int id;

    @JsonView({Views.FlightView.class, Views.PassengerView.class})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departure;

    @JsonView({Views.FlightView.class, Views.PassengerView.class})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrival;

    @ManyToOne
    @JsonView({Views.FlightView.class, Views.PassengerView.class})
    private Airport origin;

    @ManyToOne
    @JsonView({Views.FlightView.class, Views.PassengerView.class})
    private Airport destination;

    @ManyToOne
    @JsonView({Views.FlightView.class, Views.PassengerView.class})
    private Aircraft aircraft;

    @JsonView(Views.FlightView.class)
    private int numberOfPassengers;

    @ManyToMany(mappedBy = "flights")
    @JsonView(Views.FlightView.class)
    private List<Passenger> passengerList;

    // constructors
    public Flight() {}

    public Flight(LocalDateTime departure, LocalDateTime arrival, Airport origin, Airport destination, Aircraft aircraft, int numberOfPassengers) {
        this.departure = departure;
        this.arrival = arrival;
        this.origin = origin;
        this.destination = destination;
        this.aircraft = aircraft;
        this.numberOfPassengers = numberOfPassengers;
        this.passengerList = new ArrayList<Passenger>();
    }

    public Flight(FlightDTO flightDTO,Airport origin, Airport destination, Aircraft aircraft) {
        this.departure = flightDTO.getDeparture();
        this.arrival = flightDTO.getArrival();
        this.origin = origin;
        this.destination = destination;
        this.aircraft = aircraft;
        this.numberOfPassengers = flightDTO.getNumberOfPassengers();
        this.passengerList = new ArrayList<Passenger>();
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

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
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
