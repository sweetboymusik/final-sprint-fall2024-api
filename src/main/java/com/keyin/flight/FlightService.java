package com.keyin.flight;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftFormattedDTO;
import com.keyin.aircraft.AircraftService;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportFormattedDTO;
import com.keyin.airport.AirportService;
import com.keyin.city.CityFormattedDTO;
import com.keyin.exceptions.EntityNotFoundException;
import com.keyin.passenger.Passenger;
import com.keyin.passenger.PassengerRepository;
import com.keyin.passenger.PassengerService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private AirportService airportService;

    public Iterable<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Iterable<FlightTableDTO> getAllFlightsForTable() {
        Iterable<Flight> flights = flightRepository.findAll();
        List<FlightTableDTO> flightDTOs = new ArrayList<>();

        for (Flight flight : flights) {
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

            // create the FlightTable DTO
            FlightTableDTO flightDTO = new FlightTableDTO(flight.getId(), departureString, arrivalString, origin,
                    destination,
                    aircraft,
                    flight.getNumberOfPassengers());

            flightDTOs.add(flightDTO);
        }

        return flightDTOs;
    }

    public Flight addFlight(FlightDTO flightDTO) {
        Airport origin = airportService.getAirportById(flightDTO.getOriginAirportId());
        Airport destination = airportService.getAirportById(flightDTO.getDestinationAirportId());
        Aircraft aircraft = aircraftService.getAircraftById(flightDTO.getAircraftId());

        Flight flight = new Flight(flightDTO, origin, destination, aircraft);

        return flightRepository.save(flight);
    }

    @Transactional
    public String addPassengerById(int flightId, int passengerId) {
        Flight flight = getFlightById(flightId);
        Passenger passengerToAdd = passengerService.getPassengerById(passengerId);

        if (passengerToAdd.getFlights().contains(flight)) {
            return "Passenger is already in flight";
        }

        passengerToAdd.getFlights().add(flight);
        passengerRepository.save(passengerToAdd);

        return "Passenger added to flight successfully";
    }

    public Flight getFlightById(int id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found"));
    }

    public Flight updateFlightById(int id, FlightDTO flightDTO) {
        Flight flightToUpdate = getFlightById(id);

        if (flightDTO.getAircraftId() != 0) {
            Aircraft aircraft = aircraftService.getAircraftById(flightDTO.getAircraftId());
            flightToUpdate.setAircraft(aircraft);
        }

        if (flightDTO.getOriginAirportId() != 0) {
            Airport airport = airportService.getAirportById(flightDTO.getOriginAirportId());
            flightToUpdate.setOrigin(airport);
        }

        if (flightDTO.getDestinationAirportId() != 0) {
            Airport airport = airportService.getAirportById(flightDTO.getDestinationAirportId());
            flightToUpdate.setDestination(airport);
        }

        if (flightDTO.getArrival() != null)
            flightToUpdate.setArrival(flightDTO.getArrival());
        if (flightDTO.getDeparture() != null)
            flightToUpdate.setDeparture(flightDTO.getDeparture());
        if (flightDTO.getNumberOfPassengers() != 0)
            flightToUpdate.setNumberOfPassengers(flightDTO.getNumberOfPassengers());

        return flightRepository.save(flightToUpdate);
    }

    public void deleteFlightById(int id) {
        Flight flightToDelete = getFlightById(id);
        flightRepository.delete(flightToDelete);
    }
}
