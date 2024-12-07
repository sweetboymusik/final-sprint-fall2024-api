package com.keyin.passenger;

public class PassengerFormattedDTO {
    // instance variables
    private String fullName;

    // constructors
    public PassengerFormattedDTO(String firstName, String lastName) {
        this.fullName = firstName + " " + lastName;
    }

    // getters and setters
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
