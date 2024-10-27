package com.example.study.shop.dto;

import com.example.study.config.entity.Status;
import com.example.study.shop.entity.Shop;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopRequestDto {

    @Enumerated(EnumType.STRING)
    private Status status;

    private String name;

    private Float grade;

    private String generalAddress;

    private String detailedAddress;

    private Boolean isExpress;

    @Builder
    public ShopRequestDto(Status status, String name, Boolean isExpress) {
        this.status = status;
        this.name = name;
        this.isExpress = isExpress;
    }

    public Shop toEntity() {
        return Shop.builder()
                .name(name)
                .grade(grade)
                .status(status)
                .isExpress(isExpress)
                .build();
    }

    public ShopRequestDto toDto(Shop shop) {
        return ShopRequestDto.builder()
                .status(shop.getStatus())
                .name(shop.getName())
                .build();
    }

}
