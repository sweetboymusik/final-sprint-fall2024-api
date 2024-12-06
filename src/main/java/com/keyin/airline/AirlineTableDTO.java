package com.keyin.airline;

import java.util.List;

import com.keyin.aircraft.AircraftFormattedDTO;

public class AirlineTableDTO {

    // instance variables
    private String name;
    private String code;
    private List<AircraftFormattedDTO> aircraft;

    // constructor
    public AirlineTableDTO(String name, String code, List<AircraftFormattedDTO> aircraft) {
        this.name = name;
        this.code = code;
        this.aircraft = aircraft;
    }

    // getters and setters
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

    public List<AircraftFormattedDTO> getAircraft() {
        return this.aircraft;
    }

    public void setAircraft(List<AircraftFormattedDTO> aircraft) {
        this.aircraft = aircraft;
    }

}
