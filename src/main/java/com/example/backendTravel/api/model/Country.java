package com.example.backendTravel.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Data
@Setter
@Getter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countryid")
    private Long countryId;

    @Column(name = "continentid")
    private Long continentId;

    @Column(name = "name")
    private String name;

    @Column(name = "flagimage")
    private String flagImage;

    @Column(name = "description")
    private String description;

}