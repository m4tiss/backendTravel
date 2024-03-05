package com.example.backendTravel.api.controller;
import com.example.backendTravel.api.model.Country;
import com.example.backendTravel.api.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/getAllCountries")
    public ResponseEntity<List<Country>> getAllContinents() {
        List<Country> countries = countryService.getAllCountries();
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(countries);
        }
    }
}
