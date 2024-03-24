package com.example.backendTravel.api.controller;

import com.example.backendTravel.api.dto.FavouriteDto;
import com.example.backendTravel.api.dto.OpinionDto;
import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Favourite;
import com.example.backendTravel.api.model.User;
import com.example.backendTravel.api.repository.CityRepository;
import com.example.backendTravel.api.repository.FavouriteRepository;
import com.example.backendTravel.api.repository.UserRepository;
import com.example.backendTravel.api.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FavouriteController {

    private final FavouriteService favouriteService;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Autowired
    public FavouriteController(FavouriteService favouriteService, UserRepository userRepository, CityRepository cityRepository) {
        this.favouriteService = favouriteService;
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



        Optional<Favourite> existingFavourite = favouriteService.findByUserAndCity(user.getUserId(), favouriteDto.getCityId());
        if (existingFavourite.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        Favourite savedFavourite = favouriteService.saveFavouriteCity(favourite);

        FavouriteDto savedFavouriteDto = FavouriteDto.fromFavourite(savedFavourite);

        return ResponseEntity.ok(savedFavouriteDto);
    }

    @GetMapping("/getFavouritesByUser")
    public ResponseEntity<List<Favourite>> getFavouritesByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        List<Favourite> favourites = favouriteService.getFavouritesByUser(user.getUserId());

        if (favourites.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(favourites);
    }

    @DeleteMapping("/removeFavourite/{favouriteId}")
    public ResponseEntity<String> removeFavourite(@PathVariable Long favouriteId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        Optional<Favourite> favouriteOptional = favouriteService.findByIdAndUserId(favouriteId, user.getUserId());

        if (!favouriteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        favouriteService.removeFavouriteCity(favouriteOptional.get());
        return ResponseEntity.ok("Favourite removed successfully");
    }


}
