package com.keyin.gate;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.airport.AirportSingleDTO;
import com.keyin.views.Views;

public class GateFlightDTO {
    // instance variables
    @JsonView({ Views.FlightView.class })
    private int id;
    @JsonView({ Views.FlightView.class })
    private String gateNumber;
    @JsonView({ Views.FlightView.class })
    private AirportSingleDTO airport;

    // constructor
    public GateFlightDTO(int id, String gateNumber, AirportSingleDTO airport) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.airport = airport;
    }

    // getters and setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGateNumber() {
        return this.gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public AirportSingleDTO getAirport() {
        return this.airport;
    }

    public void setAirport(AirportSingleDTO airport) {
        this.airport = airport;
    }

}