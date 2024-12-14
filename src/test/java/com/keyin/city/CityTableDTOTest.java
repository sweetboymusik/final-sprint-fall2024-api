package com.keyin.city;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CityTableDTOTest {

    @Test
    void testConstructorAndGetters() {
        int id = 1;
        String name = "SampleCity";
        String state = "SampleState";
        int population = 50000;
        int airports = 3;

        CityTableDTO cityTableDTO = new CityTableDTO(id, name, state, population, airports);

        assertThat(cityTableDTO.getId()).isEqualTo(id);
        assertThat(cityTableDTO.getName()).isEqualTo(name);
        assertThat(cityTableDTO.getState()).isEqualTo(state);
        assertThat(cityTableDTO.getPopulation()).isEqualTo(population);
        assertThat(cityTableDTO.getAirports()).isEqualTo(airports);
    }

    @Test
    void testSetters() {
        CityTableDTO cityTableDTO = new CityTableDTO(0, null, null, 0, 0);

        cityTableDTO.setId(2);
        cityTableDTO.setName("NewCity");
        cityTableDTO.setState("NewState");
        cityTableDTO.setPopulation(75000);
        cityTableDTO.setAirports(5);

        assertThat(cityTableDTO.getId()).isEqualTo(2);
        assertThat(cityTableDTO.getName()).isEqualTo("NewCity");
        assertThat(cityTableDTO.getState()).isEqualTo("NewState");
        assertThat(cityTableDTO.getPopulation()).isEqualTo(75000);
        assertThat(cityTableDTO.getAirports()).isEqualTo(5);
    }
}