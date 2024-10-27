package com.example.study.goods.dto;

import com.example.study.config.entity.Status;
import com.example.study.goods.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class GoodsRequestDto {
    private Status status;

    private String title;

    private String thumbnail;

    private String name;

    private String subName;

    private Integer price;

    public Goods toEntity() {
        return Goods.builder()
                .name(name)
                .price(price)
                .status(status)
                .build();
    }
}
