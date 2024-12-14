package com.keyin.city;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void testSaveAndFindById() {
        City city = new City("SampleCity", "SampleState", 100000);
        City savedCity = cityRepository.save(city);

        City retrievedCity = cityRepository.findById(savedCity.getId()).orElse(null);
        assertThat(retrievedCity).isNotNull();
        assertThat(retrievedCity.getName()).isEqualTo("SampleCity");
    }

    @Test
    void testFindByName() {
        City city = new City("UniqueCity", "UniqueState", 200000);
        cityRepository.save(city);

        City foundCity = cityRepository.findByName("UniqueCity");
        assertThat(foundCity).isNotNull();
        assertThat(foundCity.getState()).isEqualTo("UniqueState");
    }

    @Test
    void testFindAllCities() {
        City city1 = new City("City1", "State1", 50000);
        City city2 = new City("City2", "State2", 75000);

        cityRepository.saveAll(List.of(city1, city2));

        Iterable<City> cities = cityRepository.findAll();
        assertThat(cities).hasSize(2);
    }
}