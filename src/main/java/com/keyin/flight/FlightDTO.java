package com.keyin.flight;

import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import com.keyin.passenger.Passenger;

import java.time.LocalDateTime;
import java.util.List;

public class FlightDTO {
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private int originAirportId;
    private int destinationAirportId;
    private int aircraftId;
    private int numberOfPassengers;

    public FlightDTO(LocalDateTime departure, LocalDateTime arrival, int originAirportId, int destinationAirportId, int aircraftId, int numberOfPassengers) {
        this.departure = departure;
        this.arrival = arrival;
        this.originAirportId = originAirportId;
        this.destinationAirportId = destinationAirportId;
        this.aircraftId = aircraftId;
        this.numberOfPassengers = numberOfPassengers;
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

    public int getOriginAirportId() {
        return originAirportId;
    }

    public void setOriginAirportId(int originAirportId) {
        this.originAirportId = originAirportId;
    }

    public int getDestinationAirportId() {
        return destinationAirportId;
    }

    public void setDestinationAirportId(int destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
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
