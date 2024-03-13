package com.example.backendTravel.api.repository;

import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository  extends JpaRepository<City, Integer> {
}
