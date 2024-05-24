package com.example.backendTravel.api.repository;

import com.example.backendTravel.api.model.Favourite;
import com.example.backendTravel.api.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository  extends JpaRepository<Favourite, Integer> {
    @Query("SELECT f FROM Favourite f WHERE f.user.userId = :userId")
    List<Favourite> findByUserId(@Param("userId") Long userId);
    Optional<Favourite> findByFavouriteIdAndUserUserId(Long favouriteId, Long userId);
    Optional<Favourite> findByUserUserIdAndCityCityId(Long cityId, Long userId);

}
