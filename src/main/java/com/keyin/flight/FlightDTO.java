package com.keyin.flight;

import java.time.LocalDateTime;

public class FlightDTO {
    // instance variables
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private int originGateId;
    private int destinationGateId;
    private int aircraftId;
    private int numberOfPassengers;

    // constructors
    public FlightDTO(LocalDateTime departure, LocalDateTime arrival, int originGateId, int destinationGateId,
            int aircraftId, int numberOfPassengers) {
        this.departure = departure;
        this.arrival = arrival;
        this.originGateId = originGateId;
        this.destinationGateId = destinationGateId;
        this.aircraftId = aircraftId;
        this.numberOfPassengers = numberOfPassengers;
    }

    // getters and setters
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

    public int getOriginGateId() {
        return originGateId;
    }

    public void setOriginGateId(int originGateId) {
        this.originGateId = originGateId;
    }

    public int getDestinationGateId() {
        return destinationGateId;
    }

    public void setDestinationGateId(int destinationGateId) {
        this.destinationGateId = destinationGateId;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}