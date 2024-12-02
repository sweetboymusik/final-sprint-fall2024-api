package com.keyin.city;

import com.keyin.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public Iterable<City> getAllCities() {
        return cityRepository.findAll();
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

        if (city.getName() != null) cityToUpdate.setName(city.getName());
        if (city.getState() != null) cityToUpdate.setState(city.getState());
        if (city.getPopulation() > 0) cityToUpdate.setPopulation(city.getPopulation());

        return cityRepository.save(cityToUpdate);
    }

    public void deleteCityById(int id) {
        City cityToDelete = getCityById(id);
        cityRepository.delete(cityToDelete);
    }
}
