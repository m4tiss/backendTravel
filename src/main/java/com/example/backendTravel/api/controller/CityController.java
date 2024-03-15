package com.example.backendTravel.api.controller;
import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Country;
import com.example.backendTravel.api.repository.CountryRepository;
import com.example.backendTravel.api.service.CityService;
import com.example.backendTravel.api.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private CountryRepository countryRepository;

    @PostMapping("/addCity")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto cityDTO) {

        City city = new City();
        city.setName(cityDTO.getName());
        city.setCityImage(cityDTO.getCityImage());
        city.setDescription(cityDTO.getDescription());
        city.setRating(cityDTO.getRating());

        Optional<Country> countryOptional = countryRepository.findById(Math.toIntExact(cityDTO.getCountryId()));
        if (countryOptional.isPresent()) {
            city.setCountry(countryOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

        City savedCity = cityService.saveCity(city);

        CityDto savedCityDto = CityDto.fromCity(savedCity);

        return ResponseEntity.ok(savedCityDto);
    }


}

