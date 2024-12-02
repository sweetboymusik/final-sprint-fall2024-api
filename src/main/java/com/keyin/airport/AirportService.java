package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.city.CityService;
import com.keyin.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityService cityService;

    public Iterable<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport addAirport(AirportDTO airportDTO) {
        City city = cityService.getCityById(airportDTO.getCityId());
        Airport airport = new Airport(airportDTO, city);

        return airportRepository.save(airport);
    }

    public Airport getAirportById(int id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Airport not found"));
    }

    public Airport getAirportByName(String name) {
        Airport airport = airportRepository.findByName(name);

        if (airport == null) {
            throw new EntityNotFoundException("Airport not found");
        }

        return airport;
    }

    public Airport updateAirportById(int id, AirportDTO airportDTO) {
        Airport airportToUpdate = getAirportById(id);

        if (airportDTO.getName() != null) airportToUpdate.setName(airportDTO.getName());
        if (airportDTO.getCode() != null) airportToUpdate.setCode(airportDTO.getCode());

        if (airportDTO.getCityId() > 0) {
            City city = cityService.getCityById(airportDTO.getCityId());
            airportToUpdate.setCity(city);
        }

        return airportRepository.save(airportToUpdate);
    }

    public void deleteAirportById(int id) {
        Airport airportToDelete = airportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Airport not found"));

        airportRepository.delete(airportToDelete);
    }
}
