package com.example.backendTravel.api.controller;

import com.example.backendTravel.api.dto.CityDto;
import com.example.backendTravel.api.dto.OpinionDto;
import com.example.backendTravel.api.model.*;
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
            City city = cityOptional.get();
            opinion.setCity(city);

            double currentRating = city.getRating();
            int numberOfOpinions = opinionService.getOpinionsByCity(city.getCityId()).size();
            float newRating = (float) (((currentRating * numberOfOpinions) + opinionDTO.getRating()) / (numberOfOpinions + 1));
            city.setRating(newRating);
            cityRepository.save(city);

        } else {
            return ResponseEntity.notFound().build();
        }

        Opinion savedOpinion = opinionService.saveOpinion(opinion);

        OpinionDto savedOpinionDto = OpinionDto.fromOpinion(savedOpinion);


        return ResponseEntity.ok(savedOpinionDto);
    }

    @GetMapping("/public/getAllOpinions")
    public ResponseEntity<List<Opinion>> getAllOpinions() {
        List<Opinion> opinions = opinionService.getAllOpinions();

        if (opinions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(opinions);
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

    @GetMapping("/getOpinionsPerUser")
    public ResponseEntity<Double> getOpinionsPerUser() {
        Long countUsers = userRepository.countUsersByRole(Role.USER);
        Long countOpinions = opinionService.countOpinions();

        if (countUsers == 0) {
            return ResponseEntity.badRequest().body(0.0);
        }

        double averageOpinionsPerUser = (double) countOpinions / countUsers;

        return ResponseEntity.ok(averageOpinionsPerUser);
    }

    @DeleteMapping("/removeOpinion/{opinionId}")
    public ResponseEntity<Void> removeOpinion(@PathVariable Long opinionId) {

        Optional<Opinion> opinionOptional = opinionService.findById(opinionId);
        if (!opinionOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Opinion opinionToRemove = opinionOptional.get();
        Long cityId = opinionToRemove.getCity().getCityId();

        opinionService.removeOpinion(opinionToRemove);

        List<Opinion> opinions = opinionService.getOpinionsByCity(cityId);
        double newRating = 0.0;
        if (!opinions.isEmpty()) {
            double totalRating = 0.0;
            for (Opinion opinion : opinions) {
                totalRating += opinion.getRating();
            }
            newRating = totalRating / opinions.size();
        }

        Optional<City> cityOptional = cityRepository.findById(Math.toIntExact(cityId));
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            city.setRating((float) newRating);
            cityRepository.save(city);
        }

        return ResponseEntity.ok().build();
    }


    @GetMapping("/getMostActiveUser")
    public ResponseEntity<String> getMostActiveUser() {
        User mostActiveUser = opinionService.getMostActiveUser();
        return ResponseEntity.ok().body(mostActiveUser.getNickname());
    }

    @GetMapping("/getUserOpinions")
    public ResponseEntity<List<Opinion>> getUserOpinions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();

        List<Opinion> userOpinions = opinionService.getOpinionsByUserId(user.getUserId());

        return ResponseEntity.ok(userOpinions);
    }

}
