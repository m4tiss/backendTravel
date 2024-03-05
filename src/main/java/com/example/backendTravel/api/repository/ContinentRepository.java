package com.example.backendTravel.api.repository;

import com.example.backendTravel.api.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {
}
