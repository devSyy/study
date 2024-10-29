package com.example.study.category.dto;

import com.example.study.config.entity.Status;
import com.example.study.shop.entity.ShopCategoryRelation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LookupCategoryRes {

    private Long categoryId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String name;

    public LookupCategoryRes(ShopCategoryRelation shopCategoryRelation){
        this.categoryId = shopCategoryRelation.getCategory().getCategoryId();
        this.name = shopCategoryRelation.getCategory().getName();
        this.status = shopCategoryRelation.getCategory().getStatus();
    }
}
