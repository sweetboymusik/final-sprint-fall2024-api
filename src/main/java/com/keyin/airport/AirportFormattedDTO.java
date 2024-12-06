package com.keyin.airport;

import com.keyin.city.CityFormattedDTO;

public class AirportFormattedDTO {
    // instance variables
    private String formattedName;

    // constructors
    public AirportFormattedDTO(String name, String code) {
        this.formattedName = name + " (" + code + ")";
    }

    public AirportFormattedDTO(String name, String code, CityFormattedDTO city) {
        this.formattedName = name + " (" + code + ") | " + city.getFormattedAddress();
    }

    // getters and setters
    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }
}
