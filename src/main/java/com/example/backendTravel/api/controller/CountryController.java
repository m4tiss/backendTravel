package com.example.backendTravel.api.controller;
import com.example.backendTravel.api.dto.CountryDto;
import com.example.backendTravel.api.model.Continent;
import com.example.backendTravel.api.model.Country;
import com.example.backendTravel.api.repository.ContinentRepository;
import com.example.backendTravel.api.repository.CountryRepository;
import com.example.backendTravel.api.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Autowired
    private ContinentRepository continentRepository;
    @PostMapping("/addCountry")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto countryDto) {
        Country country = new Country();
        country.setName(countryDto.getName());
        country.setFlagImage(countryDto.getFlagImage());
        country.setDescription(countryDto.getDescription());

        Optional<Continent> continentOptional = continentRepository.findById(Math.toIntExact(countryDto.getContinentId()));
        if (continentOptional.isPresent()) {
            country.setContinent(continentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

        Country savedCountry = countryService.saveCountry(country);

        CountryDto savedCountryDto = CountryDto.fromCountry(savedCountry);

        return ResponseEntity.ok(savedCountryDto);
    }

}
