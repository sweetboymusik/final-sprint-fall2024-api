package com.keyin.airport;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.keyin.city.City;
import com.keyin.gate.GateTableDTO;
import com.keyin.views.Views;

public class AirportSingleDTO {
    // instance variables
    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private int id;
    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private String name;
    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private String code;
    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private City city;
    @JsonView({ Views.CityView.class, Views.AirportView.class, Views.FlightView.class, Views.PassengerView.class })
    private List<GateTableDTO> gates;

    // constructors
    public AirportSingleDTO(Airport airport, List<GateTableDTO> gates) {
        this.id = airport.getId();
        this.name = airport.getName();
        this.code = airport.getCode();
        this.city = airport.getCity();
        this.gates = gates;
    }

    public AirportSingleDTO(int id, String name, String code, City city) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.city = city;
        this.gates = null;
    }

    // getters and setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<GateTableDTO> getGates() {
        return this.gates;
    }

    public void setGates(List<GateTableDTO> gates) {
        this.gates = gates;
    }

}