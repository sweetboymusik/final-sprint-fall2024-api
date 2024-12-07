package com.keyin.aircraft;

import com.keyin.airline.Airline;
import com.keyin.airline.AirlineFormattedDTO;
import com.keyin.airline.AirlineService;
import com.keyin.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirlineService airlineService;

    public Iterable<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    public Iterable<AircraftTableDTO> getAllAircraftForTable() {
        Iterable<Aircraft> aircrafts = aircraftRepository.findAll();
        List<AircraftTableDTO> aircraftDTOs = new ArrayList<>();

        for (Aircraft aircraft : aircrafts) {
            AirlineFormattedDTO airlineFormatted = new AirlineFormattedDTO(aircraft.getAirline().getName());
            AircraftTableDTO aircraftTableDTO = new AircraftTableDTO(aircraft.getId(), aircraft.getType(),
                    aircraft.getPassengerCapacity(), airlineFormatted);

            aircraftDTOs.add(aircraftTableDTO);
        }

        return aircraftDTOs;
    }

    public Aircraft addAircraft(AircraftDTO aircraftDTO) {
        Airline airline = airlineService.getAirlineById(aircraftDTO.getAirlineId());
        Aircraft aircraft = new Aircraft(aircraftDTO, airline);

        return aircraftRepository.save(aircraft);
    }

    public Aircraft getAircraftById(int id) {
        return aircraftRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aircraft not found"));
    }

    public Aircraft updateAircraftById(int id, AircraftDTO aircraftDTO) {
        Aircraft aircraftToUpdate = getAircraftById(id);

        if (aircraftDTO.getType() != null)
            aircraftToUpdate.setType(aircraftDTO.getType());
        if (aircraftDTO.getPassengerCapacity() != 0)
            aircraftToUpdate.setPassengerCapacity(aircraftDTO.getPassengerCapacity());

        if (aircraftDTO.getAirlineId() != 0) {
            Airline airline = airlineService.getAirlineById(aircraftDTO.getAirlineId());
            aircraftToUpdate.setAirline(airline);
        }

        return aircraftRepository.save(aircraftToUpdate);
    }

    public void deleteAircraftById(int id) {
        Aircraft aircraftToDelete = getAircraftById(id);
        aircraftRepository.delete(aircraftToDelete);
    }
}
