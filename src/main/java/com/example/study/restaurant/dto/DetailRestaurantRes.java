package com.example.study.restaurant.dto;

import com.example.study.config.entity.Status;
import com.example.study.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailRestaurantRes {

    private Long restaurantId;
    private Status status;
    private String name;
    private Float grade;
    private String generalAddress;
    private String detailedAddress;
    private Boolean isExpress;

    public DetailRestaurantRes(Restaurant restaurant) {

        this.restaurantId = restaurant.getRestaurantId();
        this.status = restaurant.getStatus();
        this.name = restaurant.getName();
        this.grade = restaurant.getGrade();
    }
}