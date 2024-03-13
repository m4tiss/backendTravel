package com.example.backendTravel.api.controller;
import com.example.backendTravel.api.model.Country;
import com.example.backendTravel.api.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/getCountry/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") Integer id) {
        Optional<Country> countryOptional = countryService.getCountry(id);
        if (countryOptional.isPresent()) {
            return ResponseEntity.ok(countryOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
