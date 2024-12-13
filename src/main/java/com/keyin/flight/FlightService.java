package com.keyin.flight;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftFormattedDTO;
import com.keyin.aircraft.AircraftService;
import com.keyin.airport.AirportFormattedDTO;
import com.keyin.city.CityFormattedDTO;
import com.keyin.exceptions.EntityNotFoundException;
import com.keyin.gate.Gate;
import com.keyin.gate.GateRepository;
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
    private GateRepository gateRepository;

    @Autowired
    private AircraftService aircraftService;

    public Iterable<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Iterable<FlightTableDTO> getAllFlightsForTable() {
        Iterable<Flight> flights = flightRepository.findAll();
        List<FlightTableDTO> flightDTOs = new ArrayList<>();

        for (Flight flight : flights) {
            // get origin info
            CityFormattedDTO originCity = new CityFormattedDTO(
                    flight.getOriginGate().getAirport().getCity().getName(),
                    flight.getOriginGate().getAirport().getCity().getState());
            AirportFormattedDTO origin = new AirportFormattedDTO(
                    flight.getOriginGate().getAirport().getName(),
                    flight.getOriginGate().getAirport().getCode(), originCity,
                    flight.getOriginGate().getGateNumber());

            // get destination info
            CityFormattedDTO destinationCity = new CityFormattedDTO(
                    flight.getDestinationGate().getAirport().getCity().getName(),
                    flight.getDestinationGate().getAirport().getCity().getState());
            AirportFormattedDTO destination = new AirportFormattedDTO(
                    flight.getDestinationGate().getAirport().getName(),
                    flight.getDestinationGate().getAirport().getCode(), destinationCity,
                    flight.getDestinationGate().getGateNumber());

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
        Gate originGate = gateRepository.findById(flightDTO.getOriginGateId())
                .orElseThrow(() -> new RuntimeException("Origin gate not found"));

        Gate destinationGate = gateRepository.findById(flightDTO.getDestinationGateId())
                .orElseThrow(() -> new RuntimeException("Destination gate not found"));

        if (originGate.getAirport().getId() == destinationGate.getAirport().getId()) {
            throw new RuntimeException("Origin and destination gates cannot belong to the same airport");
        }

        Aircraft aircraft = aircraftService.getAircraftById(flightDTO.getAircraftId());

        Flight flight = new Flight(
                flightDTO.getDeparture(),
                flightDTO.getArrival(),
                originGate,
                destinationGate,
                aircraft,
                flightDTO.getNumberOfPassengers());

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

        int updatedPassengerCount = flight.getPassengerList().size();
        flight.setNumberOfPassengers(updatedPassengerCount);
        flightRepository.save(flight);

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

        if (flightDTO.getOriginGateId() != 0) {
            Gate originGate = gateRepository.findById(flightDTO.getOriginGateId())
                    .orElseThrow(() -> new RuntimeException("Origin gate not found"));
            flightToUpdate.setOriginGate(originGate);
        }

        if (flightDTO.getDestinationGateId() != 0) {
            Gate destinationGate = gateRepository.findById(flightDTO.getDestinationGateId())
                    .orElseThrow(() -> new RuntimeException("Destination gate not found"));
            flightToUpdate.setDestinationGate(destinationGate);
        }

        if (flightDTO.getArrival() != null) {
            flightToUpdate.setArrival(flightDTO.getArrival());
        }

        if (flightDTO.getDeparture() != null) {
            flightToUpdate.setDeparture(flightDTO.getDeparture());
        }

        if (flightDTO.getNumberOfPassengers() != 0) {
            flightToUpdate.setNumberOfPassengers(flightDTO.getNumberOfPassengers());
        }

        return flightRepository.save(flightToUpdate);
    }

    public void deleteFlightById(int id) {
        Flight flightToDelete = getFlightById(id);
        flightRepository.delete(flightToDelete);
    }
}
