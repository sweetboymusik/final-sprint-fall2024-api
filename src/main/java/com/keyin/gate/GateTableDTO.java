package com.keyin.gate;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.views.Views;

public class GateTableDTO {
    // instance variables
    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class })
    private int id;
    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class })
    private String gateNumber;
    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class })
    private int originFlights;
    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class })
    private int destinationFlights;

    // constructor
    public GateTableDTO(int id, String gateNumber, int originFlights, int destinationFlights) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.originFlights = originFlights;
        this.destinationFlights = destinationFlights;
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

    public int getOriginFlights() {
        return this.originFlights;
    }

    public void setOriginFlights(int originFlights) {
        this.originFlights = originFlights;
    }

    public int getDestinationFlights() {
        return this.destinationFlights;
    }

    public void setDestinationFlights(int destinationFlights) {
        this.destinationFlights = destinationFlights;
    }

}