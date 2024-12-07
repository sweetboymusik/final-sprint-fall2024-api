package com.keyin.flight;

public class FlightFormattedDTO {
    // instance variables
    private String flightFormatted;

    // constructors
    public FlightFormattedDTO(int id, String originInfo, String destinationInfo) {
        this.flightFormatted = "Flight #" + id + " | " + originInfo + " → " + destinationInfo;
    }

    // getters and setters
    public String getFlightFormatted() {
        return this.flightFormatted;
    }

    public void setFlightFormatted(String flightFormatted) {
        this.flightFormatted = flightFormatted;
    }

}
