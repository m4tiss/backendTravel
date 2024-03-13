package com.example.backendTravel.api.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CountryDto {
    private Long continentId;
    private String name;
    private String flagImage;
    private String description;
}
