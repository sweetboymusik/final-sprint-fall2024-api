package com.keyin.passenger;

import com.keyin.aircraft.AircraftFormattedDTO;
import com.keyin.airport.AirportFormattedDTO;
import com.keyin.city.City;
import com.keyin.city.CityFormattedDTO;
import com.keyin.city.CityService;
import com.keyin.exceptions.EntityNotFoundException;
import com.keyin.flight.Flight;
import com.keyin.flight.FlightTableDTO;

import java.time.format.DateTimeFormatter;
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
            int flights = 0;

            for (@SuppressWarnings("unused")
            Flight flight : passenger.getFlights()) {
                flights++;
            }

            CityFormattedDTO city = new CityFormattedDTO(passenger.getCity().getName(), passenger.getCity().getState());

            PassengerTableDTO passengerTableDTO = new PassengerTableDTO(passenger.getId(), passenger.getFirstName(),
                    passenger.getLastName(), passenger.getPhoneNumber(), passenger.getEmail(), city,
                    flights);

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

    public PassengerDetailsDTO getPassengerDetailsById(int id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found"));

        List<FlightTableDTO> flightTables = new ArrayList<>();

        for (Flight flight : passenger.getFlights()) {
            System.out.println(flight);
            // get origin info
            CityFormattedDTO originCity = new CityFormattedDTO(flight.getOrigin().getCity().getName(),
                    flight.getOrigin().getCity().getState());
            AirportFormattedDTO origin = new AirportFormattedDTO(flight.getOrigin().getName(),
                    flight.getOrigin().getCode(), originCity);

            // get destination info
            CityFormattedDTO destinationCity = new CityFormattedDTO(flight.getDestination().getCity().getName(),
                    flight.getDestination().getCity().getState());
            AirportFormattedDTO destination = new AirportFormattedDTO(flight.getDestination().getName(),
                    flight.getDestination().getCode(), destinationCity);

            // get departure/arrival as string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");
            String departureString = flight.getDeparture().format(formatter);
            String arrivalString = flight.getArrival().format(formatter);

            // get aircraft details
            AircraftFormattedDTO aircraft = new AircraftFormattedDTO(flight.getAircraft().getId(),
                    flight.getAircraft().getType(), flight.getAircraft().getAirline().getName());

            FlightTableDTO flightTable = new FlightTableDTO(flight.getId(), departureString, arrivalString,
                    origin, destination,
                    aircraft, flight.getNumberOfPassengers());

            flightTables.add(flightTable);
        }

        return new PassengerDetailsDTO(
                passenger.getId(),
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getPhoneNumber(),
                passenger.getEmail(),
                passenger.getCity(),
                flightTables);
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
