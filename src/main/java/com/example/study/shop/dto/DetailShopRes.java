package com.example.study.shop.dto;

import com.example.study.config.entity.Status;
import com.example.study.goods.dto.LookupGoodsRes;
import com.example.study.shop.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailShopRes {
    private Long shopId;
    private Status status;
    private String name;
    private Float grade;
    private String generalAddress;
    private String detailedAddress;
    private Boolean isExpress;

    List<LookupGoodsRes> goodsRelations = new ArrayList<>();

    public DetailShopRes(Shop shop) {
        this.shopId = shop.getShopId();
        this.status = shop.getStatus();
        this.name = shop.getName();
        this.grade = shop.getGrade();
        this.goodsRelations =
                shop.getGoodsRelations().stream()
                        .filter(m -> m.getStatus() != Status.Deleted)
                        .map(LookupGoodsRes::new).collect(Collectors.toList());
    }
}
