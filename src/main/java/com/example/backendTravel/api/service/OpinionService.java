package com.example.backendTravel.api.service;

import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Opinion;
import com.example.backendTravel.api.repository.CityRepository;
import com.example.backendTravel.api.repository.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpinionService {
    private final OpinionRepository opinionRepository;


    @Autowired
    public OpinionService(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    public Opinion saveOpinion(Opinion opinion) {
        return opinionRepository.save(opinion);
    }

}
