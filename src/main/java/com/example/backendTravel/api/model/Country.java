package com.example.backendTravel.api.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "countries")
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countryid")
    private Long countryId;

    @ManyToOne
    @JoinColumn(name = "continentid")
    private Continent continentId;

    @Column(name = "name")
    private String name;

    @Column(name = "flagimage")
    private String flagImage;

    @Column(name = "description")
    private String description;

}