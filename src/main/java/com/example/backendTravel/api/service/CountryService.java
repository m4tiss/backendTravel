package com.example.backendTravel.api.service;


import com.example.backendTravel.api.model.Country;
import com.example.backendTravel.api.repository.CountryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Optional<Country> getCountry(Integer id){
        return countryRepository.findById(id);
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

}