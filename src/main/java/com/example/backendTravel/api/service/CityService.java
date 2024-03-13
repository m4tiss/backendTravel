package com.example.backendTravel.api.service;

import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Continent;
import com.example.backendTravel.api.repository.CityRepository;
import com.example.backendTravel.api.repository.ContinentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<City> getCity(Integer id){
        return cityRepository.findById(id);
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

}
