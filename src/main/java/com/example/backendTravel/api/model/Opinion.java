package com.example.backendTravel.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "opinions")
@Data
public class Opinion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opinionid")
    private Long opinionId;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cityid")
    private City city;


    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private float rating;

    @Column(name = "opinionimage")
    private String opinionImage;

}