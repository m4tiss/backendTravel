package com.example.backendTravel.api.dto;

import com.example.backendTravel.api.model.Country;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CityDto {
    private Long countryId;
    private String name;
    private String cityImage;
    private String description;
    private float rating;
}