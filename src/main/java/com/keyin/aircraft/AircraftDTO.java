package com.keyin.aircraft;

public class AircraftDTO {
    private String type;
    private int passengerCapacity;
    private int airlineId;

    public AircraftDTO(String type, int passengerCapacity, int airlineId) {
        this.type = type;
        this.passengerCapacity = passengerCapacity;
        this.airlineId = airlineId;
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

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }
}
