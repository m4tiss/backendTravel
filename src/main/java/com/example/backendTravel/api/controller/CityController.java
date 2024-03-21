package com.example.backendTravel.api.controller;
import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Country;
import com.example.backendTravel.api.repository.CountryRepository;
import com.example.backendTravel.api.service.CityService;
import com.example.backendTravel.api.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/public/getCity/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Integer id) {
        Optional<City> cityOptional = cityService.getCity(id);
        if (cityOptional.isPresent()) {
            return ResponseEntity.ok(cityOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/public/getAllCities")
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
        city.setPopulation(cityDTO.getPopulation());

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

    @PutMapping("/updateCity/{id}")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long id, @RequestBody CityDto cityDTO) {
        Optional<City> cityOptional = cityService.getCity(Math.toIntExact(id));
        if (!cityOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        City city = cityOptional.get();
        city.setName(cityDTO.getName());
        city.setDescription(cityDTO.getDescription());
        city.setPopulation(cityDTO.getPopulation());

        City updatedCity = cityService.saveCity(city);
        CityDto updatedCityDto = CityDto.fromCity(updatedCity);

        return ResponseEntity.ok(updatedCityDto);
    }

    @GetMapping("/public/getMostPopularCities")
    public ResponseEntity<List<City>> getMostPopularCities() {
        List<City> popularCities = cityService.getMostPopularCities();
        return ResponseEntity.ok().body(popularCities);
    }

    @GetMapping("/public/getCitiesByContinent/{continent}")
    public ResponseEntity<List<City>> getCitiesByContinent(@PathVariable("continent") String continent) {
        List<City> citiesByContinent = cityService.getCitiesByContinent(continent);
        if (citiesByContinent.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(citiesByContinent);
        }
    }
}

