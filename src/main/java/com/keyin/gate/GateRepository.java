package com.keyin.gate;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keyin.airport.Airport;

@Repository
public interface GateRepository extends CrudRepository<Gate, Integer> {
    List<Gate> findByAirport(Airport airport);
}