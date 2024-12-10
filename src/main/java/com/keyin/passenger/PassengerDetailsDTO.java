package com.keyin.passenger;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.city.City;
import com.keyin.flight.FlightTableDTO;
import com.keyin.views.Views;

public class PassengerDetailsDTO {
    // instance variables

    @JsonView({ Views.PassengerView.class })
    private int id;
    @JsonView({ Views.PassengerView.class })
    private String firstName;
    @JsonView({ Views.PassengerView.class })
    private String lastName;
    @JsonView({ Views.PassengerView.class })
    private String phoneNumber;
    @JsonView({ Views.PassengerView.class })
    private String email;
    @JsonView({ Views.PassengerView.class })
    private City city;
    @JsonView({ Views.PassengerView.class })
    private List<FlightTableDTO> flights;

    // constructors
    public PassengerDetailsDTO(int id, String firstName, String lastName, String phoneNumber, String email, City city,
            List<FlightTableDTO> flights) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<FlightTableDTO> getFlights() {
        return this.flights;
    }

    public void setFlights(List<FlightTableDTO> flights) {
        this.flights = flights;
    }
}
