package com.example.backendTravel.api.service;

import com.example.backendTravel.api.model.Continent;
import com.example.backendTravel.api.repository.ContinentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContinentService {

    private final ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    public Optional<Continent> getContinent(Integer id){
        return continentRepository.findById(id);
    }

    public List<Continent> getAllContinents(){
        return continentRepository.findAll();
    }

}
