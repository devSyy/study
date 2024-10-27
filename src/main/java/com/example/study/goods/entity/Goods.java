package com.example.study.goods.entity;

import com.example.study.config.entity.BaseTimeEntity;
import com.example.study.config.entity.Status;
import com.example.study.shop.entity.ShopGoodsRelation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Goods extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String title;

    private String thumbnail;

    private String name;

    private String subName;

    private Integer price;

    @OneToMany(mappedBy = "goods")
    private List<ShopGoodsRelation> shopRelations = new ArrayList<>();

    @Builder
    public Goods(String name, Integer price, Status status) {
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public void addRestaurantRelation(ShopGoodsRelation shopRelation) {
        shopRelations.add(shopRelation);
        shopRelation.setGoods(this);
    }
}
