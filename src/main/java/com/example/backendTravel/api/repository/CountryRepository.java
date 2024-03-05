package com.example.backendTravel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backendTravel.api.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
