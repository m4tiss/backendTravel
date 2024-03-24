package com.example.backendTravel.api.service;

import com.example.backendTravel.api.model.Opinion;
import com.example.backendTravel.api.repository.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Opinion> getOpinionsByCity(Long cityId) {
        return opinionRepository.findByCityId(cityId);
    }

}
