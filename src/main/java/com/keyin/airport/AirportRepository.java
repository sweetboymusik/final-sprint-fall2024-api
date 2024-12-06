package com.keyin.airport;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Integer> {
    Airport findByName(String name);

    @Query("SELECT a FROM Airport a LEFT JOIN FETCH a.city c")
    Iterable<AirportTableDTO> findAllForTable();
}
