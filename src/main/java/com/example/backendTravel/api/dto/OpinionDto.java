package com.example.backendTravel.api.dto;

import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Opinion;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OpinionDto {
    private Long cityId;
    private String title;
    private String description;
    private float rating;
    private String opinionImage;

    public static OpinionDto fromOpinion(Opinion opinion) {
        OpinionDto opinionDto = new OpinionDto();
        opinionDto.setCityId(opinion.getCity().getCityId());
        opinionDto.setTitle(opinion.getTitle());
        opinionDto.setDescription(opinion.getDescription());
        opinionDto.setRating(opinion.getRating());
        opinionDto.setOpinionImage(opinion.getOpinionImage());
        return opinionDto;
    }
}