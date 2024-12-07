package com.keyin.city;

public class CityFormattedDTO {
    // instance variables
    private String formattedAddress;

    // constructors
    public CityFormattedDTO(String name, String state) {
        this.formattedAddress = name + ", " + state;
    }

    // getters and setters
    public String getFormattedAddress() {
        return this.formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

}
