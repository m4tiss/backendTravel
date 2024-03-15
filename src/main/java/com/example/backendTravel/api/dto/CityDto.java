package com.example.backendTravel.api.dto;

import com.example.backendTravel.api.model.City;
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

    public static CityDto fromCity(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setCountryId(city.getCountry().getCountryId());
        cityDto.setName(city.getName());
        cityDto.setCityImage(city.getCityImage());
        cityDto.setDescription(city.getDescription());
        cityDto.setRating(city.getRating());
        return cityDto;
    }
}