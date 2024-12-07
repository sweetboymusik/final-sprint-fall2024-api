package com.keyin.aircraft;

import com.keyin.airline.AirlineFormattedDTO;

public class AircraftTableDTO {
    // instance variables
    private int id;
    private String type;
    private int passengerCapacity;
    private AirlineFormattedDTO airline;

    // constructors
    public AircraftTableDTO(int id, String type, int passengerCapacity, AirlineFormattedDTO airline) {
        this.id = id;
        this.type = type;
        this.passengerCapacity = passengerCapacity;
        this.airline = airline;
    }

    // getters and setters
    public int getId() {
        return this.id;
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

    public AirlineFormattedDTO getAirline() {
        return this.airline;
    }

    public void setAirline(AirlineFormattedDTO airline) {
        this.airline = airline;
    }

}
