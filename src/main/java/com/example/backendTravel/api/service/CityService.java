package com.example.backendTravel.api.service;

import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.repository.CityRepository;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<City> getCity(Integer id) {
        return cityRepository.findById(id);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public List<City> getMostPopularCities() {
        return cityRepository.getMostPopularCities();
    }

}
