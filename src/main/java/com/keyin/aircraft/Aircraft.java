package com.keyin.aircraft;

import com.fasterxml.jackson.annotation.*;
import com.keyin.airline.Airline;
import com.keyin.views.Views;
import jakarta.persistence.*;

@Entity
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.AircraftView.class, Views.AirlineView.class, Views.FlightView.class, Views.PassengerView.class})

    private int id;

    @JsonView({Views.AircraftView.class, Views.AirlineView.class, Views.FlightView.class, Views.PassengerView.class})
    private String type;

    @JsonView({Views.AircraftView.class, Views.AirlineView.class, Views.FlightView.class, Views.PassengerView.class})
    private int passengerCapacity;

    @ManyToOne
    @JsonView({Views.AircraftView.class, Views.FlightView.class, Views.PassengerView.class})
    private Airline airline;

    // constructors
    public Aircraft() {
    }

    public Aircraft(String type, int passengerCapacity, Airline airline) {
        this.type = type;
        this.passengerCapacity = passengerCapacity;
        this.airline = airline;
    }

    public Aircraft(AircraftDTO aircraftDTO, Airline airline) {
        this.type = aircraftDTO.getType();
        this.passengerCapacity = aircraftDTO.getPassengerCapacity();
        this.airline = airline;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
