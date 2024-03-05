package com.example.backendTravel.api.controller;
import com.example.backendTravel.api.model.Continent;
import com.example.backendTravel.api.service.ContinentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContinentController {

    private ContinentService continentService;

    public ContinentController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping("/getAllContinents")
    public ResponseEntity<List<Continent>> getAllContinents() {
        List<Continent> continents = continentService.getAllContinents();
        if (continents.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(continents);
        }
    }
}
