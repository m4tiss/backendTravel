package com.example.backendTravel.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "continents")
@Data
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "continentid")
    private Long continentId;

    @Column(name = "name")
    private String name;

}