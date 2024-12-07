package com.keyin.airline;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftFormattedDTO;
import com.keyin.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    public Iterable<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Iterable<AirlineTableDTO> getAllAirlinesForTable() {
        Iterable<Airline> airlines = airlineRepository.findAll();
        List<AirlineTableDTO> airlineDTOs = new ArrayList<>();

        for (Airline airline : airlines) {
            List<AircraftFormattedDTO> aircraftList = new ArrayList<>();

            for (Aircraft aircraft : airline.getAircraftList()) {
                AircraftFormattedDTO aircraftFormatted = new AircraftFormattedDTO(aircraft.getId(), aircraft.getType());

                aircraftList.add(aircraftFormatted);

            }

            AirlineTableDTO arlineFormattedDTO = new AirlineTableDTO(airline.getId(), airline.getName(),
                    airline.getCountry(),
                    aircraftList);

            airlineDTOs.add(arlineFormattedDTO);
        }

        return airlineDTOs;
    }

    public Airline addAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline getAirlineById(int id) {
        return airlineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Airline not found"));
    }

    public Airline getAirlineByName(String name) {
        Airline airline = airlineRepository.findByName(name);

        if (airline == null) {
            throw new EntityNotFoundException("Airline not found");
        }

        return airline;
    }

    public Airline updateAirlineById(int id, Airline airline) {
        Airline airlineToUpdate = getAirlineById(id);

        if (airline.getName() != null)
            airlineToUpdate.setName(airline.getName());
        if (airline.getCountry() != null)
            airlineToUpdate.setCountry(airline.getCountry());

        return airlineRepository.save(airlineToUpdate);
    }

    public void deleteAirlineById(int id) {
        Airline airlineToDelete = getAirlineById(id);
        airlineRepository.delete(airlineToDelete);
    }
}
