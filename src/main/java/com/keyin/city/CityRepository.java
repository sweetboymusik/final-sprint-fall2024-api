package com.keyin.city;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
    public City findByName(String name);

    @Query("SELECT c FROM City c LEFT JOIN FETCH c.airports a")
    Iterable<CityTableDTO> findAllForTable();
}
