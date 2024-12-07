package com.keyin.passenger;

import java.util.List;

import com.keyin.city.CityFormattedDTO;
import com.keyin.flight.FlightFormattedDTO;

public class PassengerTableDTO {
    // instance variables
    private int id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private CityFormattedDTO city;
    private List<FlightFormattedDTO> flights;

    // constructor
    public PassengerTableDTO(int id, String firstName, String lastName, String phoneNumber, String email,
            CityFormattedDTO city,
            List<FlightFormattedDTO> flights) {
        this.id = id;
        this.fullName = firstName + " " + lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.flights = flights;
    }

    // getters and setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CityFormattedDTO getCity() {
        return this.city;
    }

    public void setCity(CityFormattedDTO city) {
        this.city = city;
    }

    public List<FlightFormattedDTO> getFlights() {
        return this.flights;
    }

    public void setFlights(List<FlightFormattedDTO> flights) {
        this.flights = flights;
    }
}
