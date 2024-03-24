package com.example.backendTravel.api.controller;

import com.example.backendTravel.api.dto.FavouriteDto;
import com.example.backendTravel.api.dto.OpinionDto;
import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Favourite;
import com.example.backendTravel.api.model.User;
import com.example.backendTravel.api.repository.CityRepository;
import com.example.backendTravel.api.repository.FavouriteRepository;
import com.example.backendTravel.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FavouriteController {

    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Autowired
    public FavouriteController(FavouriteRepository favouriteRepository, UserRepository userRepository, CityRepository cityRepository) {
        this.favouriteRepository = favouriteRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @PostMapping("/addFavourite")
    public ResponseEntity<FavouriteDto> addFavourite(@RequestBody FavouriteDto favouriteDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        Favourite favourite = new Favourite();
        favourite.setUser(user);

        Optional<City> cityOptional = cityRepository.findById(Math.toIntExact(favouriteDto.getCityId()));
        if (cityOptional.isPresent()) {
            favourite.setCity(cityOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

        Favourite savedFavourite = favouriteRepository.save(favourite);

        FavouriteDto savedFavouriteDto = FavouriteDto.fromFavourite(savedFavourite);

        return ResponseEntity.ok(savedFavouriteDto);
    }


}
