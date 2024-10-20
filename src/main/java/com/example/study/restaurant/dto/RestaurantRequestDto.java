package com.example.study.restaurant.dto;

import com.example.study.config.entity.Status;
import com.example.study.restaurant.entity.Restaurant;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.study.config.entity.Status.Valid;

@Getter
@NoArgsConstructor
public class RestaurantRequestDto {

    @Enumerated(EnumType.STRING)
    private Status status;

    private String name;

    private Float grade;

    private String generalAddress;

    private String detailedAddress;

    private Boolean isExpress;

    @Builder
    public RestaurantRequestDto(Status status, String name, Boolean isExpress) {
        this.status = status;
        this.name = name;
        this.isExpress = isExpress;
    }

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .grade(grade)
                .status(status)
                .isExpress(isExpress)
                .build();
    }

    public RestaurantRequestDto toDto(Restaurant restaurant) {
        return RestaurantRequestDto.builder()
                .status(restaurant.getStatus())
                .name(restaurant.getName())
                .build();
    }

}
