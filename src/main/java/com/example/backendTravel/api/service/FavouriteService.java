package com.example.backendTravel.api.service;

import com.example.backendTravel.api.model.Favourite;
import com.example.backendTravel.api.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Favourite> getFavouritesByUser(Long userId) {
        return favouriteRepository.findByUserId(userId);
    }

    public Optional<Favourite> findByIdAndUserId(Long favouriteId, Long userId){return favouriteRepository.findByFavouriteIdAndUserUserId(favouriteId,userId);}
    public Optional<Favourite> findByUserAndCity(Long cityId, Long userId){return favouriteRepository.findByUserUserIdAndCityCityId(cityId,userId);}

    public void removeFavouriteCity(Favourite favourite) { favouriteRepository.delete(favourite);}
}
