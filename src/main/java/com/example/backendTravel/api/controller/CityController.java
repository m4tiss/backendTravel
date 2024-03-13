package com.example.backendTravel.api.controller;
import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Continent;
import com.example.backendTravel.api.service.CityService;
import com.example.backendTravel.api.service.ContinentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getAllCities")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        if (cities.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cities);
        }
    }
}
