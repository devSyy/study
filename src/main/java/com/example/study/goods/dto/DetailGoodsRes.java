package com.example.study.goods.dto;

import com.example.study.config.entity.Status;
import com.example.study.goods.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailGoodsRes implements Serializable {

    private static final long serialVersionUID = 3L;

    private Long goodsId;

    private Status status;

    private String title;

    private String thumbnail;

    private String name;

    private String subName;

    private Integer price;


    public DetailGoodsRes(Goods goods){
        this.goodsId = goods.getGoodsId();
        this.name = goods.getName();
        this.price = goods.getPrice();
        this.status = goods.getStatus();
        this.title = goods.getTitle();
    }
}
