package com.keyin.airport;

import java.util.List;

import com.keyin.gate.GateDTO;

public class AirportDTO {
    // instance variables
    private String name;
    private String code;
    private int cityId;
    private List<GateDTO> gates;

    // constructor
    public AirportDTO(String name, String code, int cityId, List<GateDTO> gates) {
        this.name = name;
        this.code = code;
        this.cityId = cityId;
        this.gates = gates;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public List<GateDTO> getGates() {
        return gates;
    }

    public void setGates(List<GateDTO> gates) {
        this.gates = gates;
    }
}