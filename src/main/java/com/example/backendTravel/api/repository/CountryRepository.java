package com.example.backendTravel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backendTravel.api.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT c FROM Country c JOIN c.continent co WHERE co.name = :continent")
    List<Country> getCountriesByContinent(@Param("continent") String continent);

}
