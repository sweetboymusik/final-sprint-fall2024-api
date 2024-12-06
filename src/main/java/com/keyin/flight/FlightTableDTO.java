package com.keyin.flight;

import com.keyin.aircraft.AircraftFormattedDTO;
import com.keyin.airport.AirportFormattedDTO;

public class FlightTableDTO {
    // instance variables
    private int id;
    private String departure;
    private String arrival;
    private AirportFormattedDTO origin;
    private AirportFormattedDTO destination;
    private AircraftFormattedDTO aircraft;
    private int numberOfPassengers;

    // constructors
    public FlightTableDTO(int id, String departure, String arrival, AirportFormattedDTO origin,
            AirportFormattedDTO destination, AircraftFormattedDTO aircraft, int numberOfPassengers) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.origin = origin;
        this.destination = destination;
        this.aircraft = aircraft;
        this.numberOfPassengers = numberOfPassengers;
    }

    // getters and setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeparture() {
        return this.departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return this.arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public AirportFormattedDTO getOrigin() {
        return this.origin;
    }

    public void setOrigin(AirportFormattedDTO origin) {
        this.origin = origin;
    }

    public AirportFormattedDTO getDestination() {
        return this.destination;
    }

    public void setDestination(AirportFormattedDTO destination) {
        this.destination = destination;
    }

    public AircraftFormattedDTO getAircraft() {
        return this.aircraft;
    }

    public void setAircraft(AircraftFormattedDTO aircraft) {
        this.aircraft = aircraft;
    }

    public int getNumberOfPassengers() {
        return this.numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
