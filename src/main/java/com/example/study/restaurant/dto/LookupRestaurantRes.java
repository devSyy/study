package com.example.study.restaurant.dto;

import com.example.study.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public interface LookupRestaurantRes {

    Long getRestaurantId();

    String getName();

    Float getGrade();

}
