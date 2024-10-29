package com.example.study.goods.dto;

import com.example.study.config.entity.Status;
import com.example.study.goods.entity.Goods;
import com.example.study.shop.entity.ShopGoodsRelation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LookupGoodsRes {

    private Long goodsId;

    private Status status;

    private String thumbnail;

    private String name;

    private String subName;

    private Integer price;

    private Float grade;

    public LookupGoodsRes(ShopGoodsRelation shopGoodsRelation) {
        this.goodsId = shopGoodsRelation.getGoods().getGoodsId();
        this.name = shopGoodsRelation.getGoods().getName();
        this.price = shopGoodsRelation.getGoods().getPrice();
        this.status = shopGoodsRelation.getGoods().getStatus();
        this.thumbnail = shopGoodsRelation.getGoods().getThumbnail();
        this.subName = shopGoodsRelation.getGoods().getSubName();
    }

    public LookupGoodsRes(Goods goods) {
        this.goodsId = goods.getGoodsId();
        this.name = goods.getName();
        this.price = goods.getPrice();
        this.status = goods.getStatus();
        this.thumbnail = goods.getThumbnail();
        this.subName = goods.getSubName();
    }

}