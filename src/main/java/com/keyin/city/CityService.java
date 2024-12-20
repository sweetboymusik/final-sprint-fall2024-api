package com.keyin.city;

import com.keyin.airport.Airport;
import com.keyin.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public Iterable<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Iterable<CityTableDTO> getAllCitiesForTable() {
        Iterable<City> cities = cityRepository.findAll();
        List<CityTableDTO> cityDTOs = new ArrayList<>();

        for (City city : cities) {
            int airports = 0;

            for (@SuppressWarnings("unused")
            Airport airport : city.getAirports()) {
                airports++;
            }

            cityDTOs.add(new CityTableDTO(city.getId(), city.getName(), city.getState(), city.getPopulation(),
                    airports));
        }

        return cityDTOs;
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public City getCityById(int id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found"));
    }

    public City getCityByName(String name) {
        City city = cityRepository.findByName(name);

        if (city == null) {
            throw new EntityNotFoundException("City not found");
        }

        return city;
    }

    public City updateCityById(int id, City city) {
        City cityToUpdate = getCityById(id);

        if (city.getName() != null)
            cityToUpdate.setName(city.getName());
        if (city.getState() != null)
            cityToUpdate.setState(city.getState());
        if (city.getPopulation() > 0)
            cityToUpdate.setPopulation(city.getPopulation());

        return cityRepository.save(cityToUpdate);
    }

    public void deleteCityById(int id) {
        City cityToDelete = getCityById(id);
        cityRepository.delete(cityToDelete);
    }

}
