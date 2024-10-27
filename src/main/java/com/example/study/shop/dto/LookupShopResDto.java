package com.example.study.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class LookupShopResDto implements Serializable {
    private static final long serialVersionUID = 2L;
    private Long shopId;

    private String name;

    private Float grade;

}
