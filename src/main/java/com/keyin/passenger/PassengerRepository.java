package com.keyin.passenger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Integer> {
    Passenger findByFirstNameAndLastName(String firstName, String lastName);
}
