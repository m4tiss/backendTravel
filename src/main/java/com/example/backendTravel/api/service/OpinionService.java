package com.example.backendTravel.api.service;

import com.example.backendTravel.api.model.Opinion;
import com.example.backendTravel.api.model.User;
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

    public List<Opinion> getAllOpinions() {
        return opinionRepository.findAll();
    }

    public List<Opinion> getOpinionsByCity(Long cityId) {
        return opinionRepository.findByCityId(cityId);
    }

    public Long countOpinions() {
        return opinionRepository.count();
    }

    public User getMostActiveUser() {
        return opinionRepository.findMostActiveUser();
    }
    public Optional<Opinion> findById(Long opinionId) { return opinionRepository.findById(Math.toIntExact(opinionId)); }
    public void removeOpinion(Opinion opinion) { opinionRepository.delete(opinion); }

    public List<Opinion> getOpinionsByUserId(Long userId) { return opinionRepository.findByUserId(userId); }

}
