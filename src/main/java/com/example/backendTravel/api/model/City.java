package com.example.backendTravel.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cities")
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityid")
    private Long cityId;

    @ManyToOne
    @JoinColumn(name = "countryid")
    private Country countryId;

    @Column(name = "name")
    private String name;

    @Column(name = "cityimage")
    private String cityImage;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private float rating;


}