package com.example.backendTravel.api.repository;

import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface CityRepository  extends JpaRepository<City, Integer> {

    @Query("SELECT c FROM City c ORDER BY c.rating DESC LIMIT 6")
    List<City> getMostPopularCities();


    @Query("SELECT city FROM City city JOIN city.country country JOIN country.continent continent WHERE continent.name = :continent")
    List<City> getByContinent(@Param("continent") String continent);
}
