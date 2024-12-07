package com.keyin.passenger;

import com.keyin.city.City;
import com.keyin.city.CityFormattedDTO;
import com.keyin.city.CityService;
import com.keyin.exceptions.EntityNotFoundException;
import com.keyin.flight.Flight;
import com.keyin.flight.FlightFormattedDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private CityService cityService;

    public Iterable<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Iterable<PassengerTableDTO> getAllPassengersForTable() {
        Iterable<Passenger> passengers = passengerRepository.findAll();
        List<PassengerTableDTO> passengerDTOs = new ArrayList<>();

        for (Passenger passenger : passengers) {
            List<FlightFormattedDTO> flightFormattedDTOs = new ArrayList<>();

            for (Flight flight : passenger.getFlights()) {
                FlightFormattedDTO flightFormattedDTO = new FlightFormattedDTO(flight.getId(),
                        flight.getOrigin().getCode(), flight.getDestination().getCode());
                flightFormattedDTOs.add(flightFormattedDTO);
            }

            CityFormattedDTO city = new CityFormattedDTO(passenger.getCity().getName(), passenger.getCity().getState());

            PassengerTableDTO passengerTableDTO = new PassengerTableDTO(passenger.getId(), passenger.getFirstName(),
                    passenger.getLastName(), passenger.getPhoneNumber(), passenger.getEmail(), city,
                    flightFormattedDTOs);

            passengerDTOs.add(passengerTableDTO);
        }

        return passengerDTOs;
    }

    public Passenger addPassenger(PassengerDTO passengerDTO) {
        City city = cityService.getCityById(passengerDTO.getCityId());
        Passenger passenger = new Passenger(passengerDTO, city);

        return passengerRepository.save(passenger);
    }

    public Passenger getPassengerById(int id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found"));
    }

    public Passenger getPassengerByName(String name) {
        String[] names = name.split("_");
        String firstName = names[0];
        String lastName = names[1];

        Passenger passenger = passengerRepository.findByFirstNameAndLastName(firstName, lastName);

        if (passenger == null) {
            throw new EntityNotFoundException("Passenger not found");
        }

        return passenger;
    }

    public Passenger updatePassengerById(int id, PassengerDTO passengerDTO) {
        Passenger passengerToUpdate = getPassengerById(id);

        if (passengerDTO.getFirstName() != null)
            passengerToUpdate.setFirstName(passengerDTO.getFirstName());
        if (passengerDTO.getLastName() != null)
            passengerToUpdate.setLastName(passengerDTO.getLastName());
        if (passengerDTO.getEmail() != null)
            passengerToUpdate.setEmail(passengerDTO.getEmail());
        if (passengerDTO.getPhoneNumber() != null)
            passengerToUpdate.setPhoneNumber(passengerDTO.getPhoneNumber());

        if (passengerDTO.getCityId() > 0) {
            City city = cityService.getCityById(passengerDTO.getCityId());
            passengerToUpdate.setCity(city);
        }

        return passengerRepository.save(passengerToUpdate);
    }

    public void deletePassengerById(int id) {
        Passenger passengerToDelete = getPassengerById(id);
        passengerRepository.delete(passengerToDelete);
    }
}
