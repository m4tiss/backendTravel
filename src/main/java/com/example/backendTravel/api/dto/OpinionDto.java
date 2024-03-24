package com.example.backendTravel.api.dto;

import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Opinion;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class OpinionDto {
//    private Long userId;
    private Long cityId;
    private String title;
    private String description;
    private float rating;

    public static OpinionDto fromOpinion(Opinion opinion) {
        OpinionDto opinionDto = new OpinionDto();
//        opinionDto.setUserId(opinion.getUser().getUserId());
        opinionDto.setCityId(opinion.getCity().getCityId());
        opinionDto.setTitle(opinion.getTitle());
        opinionDto.setDescription(opinion.getDescription());
        opinionDto.setRating(opinion.getRating());
        return opinionDto;
    }
}