package com.example.backendTravel.api.controller;

import com.example.backendTravel.api.dto.CityDto;
import com.example.backendTravel.api.dto.OpinionDto;
import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Country;
import com.example.backendTravel.api.model.Opinion;
import com.example.backendTravel.api.model.User;
import com.example.backendTravel.api.repository.CityRepository;
import com.example.backendTravel.api.repository.CountryRepository;
import com.example.backendTravel.api.repository.UserRepository;
import com.example.backendTravel.api.service.OpinionService;
import com.example.backendTravel.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OpinionController {

    private OpinionService opinionService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }

    @PostMapping("/addOpinion")
    public ResponseEntity<OpinionDto> addOpinion(@RequestBody OpinionDto opinionDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();

        Opinion opinion = new Opinion();
        opinion.setTitle(opinionDTO.getTitle());
        opinion.setDescription(opinionDTO.getDescription());
        opinion.setRating(opinionDTO.getRating());
        opinion.setOpinionImage(opinionDTO.getOpinionImage());
        opinion.setUser(user);

        Optional<City> cityOptional = cityRepository.findById(Math.toIntExact(opinionDTO.getCityId()));
        if (cityOptional.isPresent()) {
            opinion.setCity(cityOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

        Opinion savedOpinion = opinionService.saveOpinion(opinion);

        OpinionDto savedOpinionDto = OpinionDto.fromOpinion(savedOpinion);

        return ResponseEntity.ok(savedOpinionDto);
    }
    @GetMapping("/public/getOpinionsByCity/{cityId}")
    public ResponseEntity<List<Opinion>> getOpinionsByCity(@PathVariable Long cityId) {
        List<Opinion> opinions = opinionService.getOpinionsByCity(cityId);

        if (opinions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(opinions);
    }

    @GetMapping("/public/getRatingByCity/{cityId}")
    public ResponseEntity<Double> getRatingByCity(@PathVariable Long cityId) {
        List<Opinion> opinions = opinionService.getOpinionsByCity(cityId);

        if (opinions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        double totalRating = 0.0;
        for (Opinion opinion : opinions) {
            totalRating += opinion.getRating();
        }
        double averageRating = totalRating / opinions.size();
        return ResponseEntity.ok(averageRating);
    }
}
