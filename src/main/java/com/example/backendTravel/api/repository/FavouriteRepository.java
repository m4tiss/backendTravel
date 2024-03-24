package com.example.backendTravel.api.repository;

import com.example.backendTravel.api.model.Favourite;
import com.example.backendTravel.api.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository  extends JpaRepository<Favourite, Integer> {
}
