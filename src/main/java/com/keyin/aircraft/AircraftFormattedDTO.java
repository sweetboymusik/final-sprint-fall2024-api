package com.keyin.aircraft;

public class AircraftFormattedDTO {
    // instance variables
    private String nameFormatted;

    // constructors
    public AircraftFormattedDTO(int id, String type) {
        this.nameFormatted = type + " (ID: " + id + ")";
    }

    // getters and setters
    public String getNameFormatted() {
        return this.nameFormatted;
    }

    public void setNameFormatted(String nameFormatted) {
        this.nameFormatted = nameFormatted;
    }

}
