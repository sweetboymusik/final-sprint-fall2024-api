package com.keyin.airport;

public class AirportDTO {
    private String name;
    private String code;
    private int cityId;

    public AirportDTO(String name, String code, int cityId) {
        this.name = name;
        this.code = code;
        this.cityId = cityId;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
