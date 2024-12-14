package com.keyin.city;

import org.junit.jupiter.api.Test;

import com.keyin.airport.Airport;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CityTest {

    @Test
    void testConstructorAndGetters() {
        String name = "SampleCity";
        String state = "SampleState";
        int population = 500000;

        City city = new City(name, state, population);

        assertThat(city.getName()).isEqualTo(name);
        assertThat(city.getState()).isEqualTo(state);
        assertThat(city.getPopulation()).isEqualTo(population);
    }

    @Test
    void testSetters() {
        City city = new City();

        city.setName("NewCity");
        city.setState("NewState");
        city.setPopulation(1000000);

        assertThat(city.getName()).isEqualTo("NewCity");
        assertThat(city.getState()).isEqualTo("NewState");
        assertThat(city.getPopulation()).isEqualTo(1000000);
    }

    @Test
    void testAirportsAssociation() {
        // Arrange
        City city = new City();
        Airport airport1 = new Airport();
        Airport airport2 = new Airport();
        List<Airport> airports = List.of(airport1, airport2);

        // Act
        city.setAirports(airports);

        // Assert
        assertThat(city.getAirports()).hasSize(2);
        assertThat(city.getAirports()).containsExactly(airport1, airport2);
    }
}