package com.example.backendTravel.api.dto;

import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Favourite;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FavouriteDto {
    private Long cityId;
    public static FavouriteDto fromFavourite(Favourite favourite) {
        FavouriteDto favouriteDto = new FavouriteDto();
        favouriteDto.setCityId(favourite.getCity().getCityId());
        return favouriteDto;
    }
}




