package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.city.CityFormattedDTO;
import com.keyin.city.CityService;
import com.keyin.exceptions.EntityNotFoundException;
import com.keyin.gate.Gate;
import com.keyin.gate.GateRepository;
import com.keyin.gate.GateTableDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private GateRepository gateRepository;

    public Iterable<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Iterable<AirportTableDTO> getAllAirportsForTable() {
        Iterable<Airport> airports = airportRepository.findAll();
        List<AirportTableDTO> airportDTOs = new ArrayList<>();

        for (Airport airport : airports) {
            int gateCount = airport.getGates().size();

            CityFormattedDTO cityFormatted = new CityFormattedDTO(airport.getCity().getName(),
                    airport.getCity().getState());
            AirportTableDTO airportTableDTO = new AirportTableDTO(airport.getId(), airport.getName(), airport.getCode(),
                    cityFormatted, gateCount);

            airportDTOs.add(airportTableDTO);
        }

        return airportDTOs;
    }

    public Airport addAirport(AirportDTO airportDTO) {
        City city = cityService.getCityById(airportDTO.getCityId());
        Airport airport = new Airport(airportDTO, city);

        return airportRepository.save(airport);
    }

    public Gate addGateToAirport(int airportId, Gate gate) {
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        gate.setAirport(airport);
        return gateRepository.save(gate);
    }

    public Airport getAirportById(int id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Airport not found"));
    }

    public AirportSingleDTO getSingleAirportById(int id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        List<GateTableDTO> gateTableItems = new ArrayList<>();

        for (Gate gate : airport.getGates()) {
            GateTableDTO gateTableDTO = new GateTableDTO(
                    gate.getId(), gate.getGateNumber(),
                    gate.getOriginFlights().size(),
                    gate.getDestinationFlights().size());

            gateTableItems.add(gateTableDTO);
        }

        AirportSingleDTO airportSingleDTO = new AirportSingleDTO(airport, gateTableItems);
        return airportSingleDTO;
    }

    public List<Gate> getGatesByAirportId(int airportId) {
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new EntityNotFoundException("Airport not found"));
        return gateRepository.findByAirport(airport);
    }

    public Airport updateAirportById(int id, AirportDTO airportDTO) {
        Airport airportToUpdate = getAirportById(id);

        if (airportDTO.getName() != null)
            airportToUpdate.setName(airportDTO.getName());
        if (airportDTO.getCode() != null)
            airportToUpdate.setCode(airportDTO.getCode());

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
