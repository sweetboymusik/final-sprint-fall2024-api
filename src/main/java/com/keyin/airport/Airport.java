package com.keyin.airport;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.city.City;
import com.keyin.gate.Gate;
import com.keyin.views.Views;
import jakarta.persistence.*;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private int id;

    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private String name;

    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private String code;

    @ManyToOne
    @JsonView({ Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private City city;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView({ Views.AirportView.class, Views.PassengerView.class })
    @JsonManagedReference
    private List<Gate> gates = new ArrayList<>();

    // constructors
    public Airport() {
    }

    public Airport(String name, String code, City city) {
        this.name = name;
        this.code = code;
        this.city = city;
        this.gates = new ArrayList<>();
    }

    public Airport(AirportDTO airportDTO, City city) {
        this.name = airportDTO.getName();
        this.code = airportDTO.getCode();
        this.city = city;
        this.gates = new ArrayList<>();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Gate> getGates() {
        return this.gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }
}
