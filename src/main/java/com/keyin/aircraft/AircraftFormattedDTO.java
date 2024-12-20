package com.keyin.aircraft;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.views.Views;

public class AircraftFormattedDTO {
    // instance variables
    @JsonView({ Views.PassengerView.class })
    private String nameFormatted;

    // constructors
    public AircraftFormattedDTO(int id, String type) {
        this.nameFormatted = type + " (ID: " + id + ")";
    }

    public AircraftFormattedDTO(int id, String type, String airline) {
        this.nameFormatted = type + " (ID: " + id + ") | " + airline;
    }

    // getters and setters
    public String getNameFormatted() {
        return this.nameFormatted;
    }

    public void setNameFormatted(String nameFormatted) {
        this.nameFormatted = nameFormatted;
    }

}
