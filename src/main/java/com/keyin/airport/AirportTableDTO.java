package com.keyin.airport;

import com.keyin.city.CityFormattedDTO;

public class AirportTableDTO {
    // instance variables
    private int id;
    private String name;
    private String code;
    private CityFormattedDTO city;

    // constructors
    public AirportTableDTO(int id, String name, String code, CityFormattedDTO cityFormatted) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.city = cityFormatted;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CityFormattedDTO getCity() {
        return this.city;
    }

    public void setCity(CityFormattedDTO cityFormatted) {
        this.city = cityFormatted;
    }
}
