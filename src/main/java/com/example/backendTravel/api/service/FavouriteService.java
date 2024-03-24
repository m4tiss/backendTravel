package com.example.backendTravel.api.service;

import com.example.backendTravel.api.model.Favourite;
import com.example.backendTravel.api.model.Opinion;
import com.example.backendTravel.api.repository.FavouriteRepository;
import com.example.backendTravel.api.repository.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteService {
    private final FavouriteRepository favouriteRepository;


    @Autowired
    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    public Favourite saveFavouriteCity(Favourite favourite) {
        return favouriteRepository.save(favourite);
    }
}
