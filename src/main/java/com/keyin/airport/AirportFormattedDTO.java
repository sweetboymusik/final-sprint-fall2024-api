package com.keyin.airport;

public class AirportFormattedDTO {
    // instance variables
    private String formattedName;

    // constructors
    public AirportFormattedDTO(String name, String code) {
        this.formattedName = name + " (" + code + ")";
    }

    // getters and setters
    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }
}
