package com.example.backendTravel.api.dto;


import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Country;
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


    public static CountryDto fromCountry(Country country) {
        CountryDto countryDto = new CountryDto();
        countryDto.setContinentId(country.getContinent().getContinentId());
        countryDto.setName(country.getName());
        countryDto.setFlagImage(country.getFlagImage());
        countryDto.setDescription(country.getDescription());
        return countryDto;
    }

}
