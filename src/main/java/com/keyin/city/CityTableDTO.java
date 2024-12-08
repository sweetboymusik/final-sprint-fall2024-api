package com.keyin.city;

public class CityTableDTO {
    private int id;
    private String name;
    private String state;
    private int population;
    private int airports;

    // Constructor
    public CityTableDTO(int id, String name, String state, int population, int airportNames) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.population = population;
        this.airports = airportNames;
    }

    // Getters and setters
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getAirports() {
        return airports;
    }

    public void setAirports(int airportNames) {
        this.airports = airportNames;
    }
}