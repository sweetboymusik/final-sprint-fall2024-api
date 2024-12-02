package com.keyin.airline;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.aircraft.Aircraft;
import com.keyin.views.Views;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.AirlineView.class, Views.AircraftView.class, Views.FlightView.class, Views.PassengerView.class})
    private int id;

    @JsonView({Views.AirlineView.class, Views.AircraftView.class, Views.FlightView.class, Views.PassengerView.class})
    private String name;

    @JsonView({Views.AirlineView.class, Views.AircraftView.class, Views.FlightView.class, Views.PassengerView.class})
    private String country;

    @OneToMany(mappedBy = "airline")
    @JsonView(Views.AirlineView.class)
    private List<Aircraft> aircraftList;

    // constructors
    public Airline() {
    }

    public Airline(String name, String country) {
        this.name = name;
        this.country = country;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }

    public void setAircraftList(List<Aircraft> aircraftList) {
        this.aircraftList = aircraftList;
    }
}
