package com.keyin.airport;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.city.CityFormattedDTO;
import com.keyin.views.Views;

public class AirportFormattedDTO {
    // instance variables
    @JsonView({ Views.PassengerView.class })
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
