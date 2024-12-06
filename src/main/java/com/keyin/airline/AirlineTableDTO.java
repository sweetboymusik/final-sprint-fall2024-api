package com.keyin.airline;

import java.util.List;

import com.keyin.aircraft.AircraftFormattedDTO;

public class AirlineTableDTO {
    // instance variables
    private int id;
    private String name;
    private String country;
    private List<AircraftFormattedDTO> aircraft;

    // constructor
    public AirlineTableDTO(int id, String name, String code, List<AircraftFormattedDTO> aircraft) {
        this.id = id;
        this.name = name;
        this.country = code;
        this.aircraft = aircraft;
    }

    // getters and setters
    public int getId() {
        return this.id;
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

    public void setCountry(String code) {
        this.country = code;
    }

    public List<AircraftFormattedDTO> getAircraft() {
        return this.aircraft;
    }

    public void setAircraft(List<AircraftFormattedDTO> aircraft) {
        this.aircraft = aircraft;
    }

}
