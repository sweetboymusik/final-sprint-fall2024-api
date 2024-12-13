package com.keyin.gate;

import com.keyin.airport.Airport;
import com.keyin.views.Views;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;

@Entity
public class Gate {
    // instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private int id;

    @JsonView({ Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private String gateNumber;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "airport_id")
    private Airport airport;

    // constructors
    public Gate() {
    }

    public Gate(String gateNumber, Airport airport) {
        this.gateNumber = gateNumber;
        this.airport = airport;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
}