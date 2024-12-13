package com.keyin.gate;

public class GateDTO {
    // instance variables
    private int id;
    private String gateNumber;

    // constructor
    public GateDTO(int id, String gateNumber) {
        this.id = id;
        this.gateNumber = gateNumber;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }
}