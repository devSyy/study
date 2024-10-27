package com.example.study.config.entity;

public enum Status {
    ON_SALE("판매중"),
    SOLD_OUT("품절"),
    Valid("운영"),
    Deleted("미운영"),
    PAYMENT_CONFIRMED("입금 확인"),
    PREPARING_PRODUCT("상품 준비"),
    READY_FOR_SHIPMENT("배송 준비"),
    WAITING_FOR_SHIPMENT("배송 대기"),
    IN_TRANSIT("배송 중"),
    DELIVERED("배송 완료");

    private final String description;

    Status(String description) {
        this.description = description;
    }
}