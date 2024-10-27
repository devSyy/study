package com.example.study.shop.entity;

import com.example.study.config.entity.BaseTimeEntity;
import com.example.study.config.entity.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Shop extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String name;

    private Float grade;

    private String generalAddress;

    private String detailedAddress;

    private Boolean isExpress;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    List<ShopGoodsRelation> goodsRelations = new ArrayList<>();

    @Builder
    public Shop(String name, Float grade, Status status, Boolean isExpress){
        this.name = name;
        this.grade = grade;
        this.status = status;
        this.isExpress = isExpress;
    }

//    public void addMenuRelation(RestaurantMenuRelation menuRelation) {
//        menuRelations.add(menuRelation);
//        menuRelation.setRestaurant(this);
//    }
}
