package com.keyin.airline;

import com.keyin.exceptions.EntityNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, Integer> {
    public Airline findByName(String name);
}
