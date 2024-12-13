package com.keyin.airport;

import com.keyin.city.CityFormattedDTO;

public class AirportTableDTO {
    // Instance variables
    private int id;
    private String name;
    private String code;
    private CityFormattedDTO city;
    private int gateCount;

    // Constructor
    public AirportTableDTO(int id, String name, String code, CityFormattedDTO city, int gateCount) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.city = city;
        this.gateCount = gateCount;
    }

    // Getters and setters
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CityFormattedDTO getCity() {
        return this.city;
    }

    public void setCity(CityFormattedDTO city) {
        this.city = city;
    }

    public int getGateCount() {
        return gateCount;
    }

    public void setGateCount(int gateCount) {
        this.gateCount = gateCount;
    }
}