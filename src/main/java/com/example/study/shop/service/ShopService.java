package com.example.study.shop.service;

import com.example.study.config.entity.Status;
import com.example.study.config.response.exception.CustomException;
import com.example.study.config.response.exception.CustomExceptionStatus;
import com.example.study.shop.dto.ShopCategoryRes;
import com.example.study.shop.dto.LookupShopRes;
import com.example.study.shop.dto.LookupShopResDto;
import com.example.study.shop.dto.ShopGoodsRes;
import com.example.study.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 코드를 더 간결하고 가독성 좋게 하기 위해서입니다. 상수를 많이 사용하는 상황에서 반복적인 클래스 참조를 줄여주는 역할


@RequiredArgsConstructor
//@Transactional(readOnly = true)
@Service
public class ShopService {

    private final ShopRepository shopRepository;

    @Cacheable(value = "shops", key = "#root.methodName", cacheManager = "redisCacheManager", unless = "#result == null || #result.isEmpty()" )
    public Page<LookupShopResDto> getAllShops(Pageable pageable, Status valid) {
        Page<LookupShopRes> projections = shopRepository.findAllByStatusOrderByUpdatedAtDesc(pageable, valid);
        List<LookupShopResDto> ListLookupRestaurantRes = projections.stream()
                .map(projection ->
                        new LookupShopResDto(projection.getShopId()
                                , projection.getName()
                                , projection.getGrade()
                ))
                .collect(Collectors.toList());
        return new PageImpl<>(ListLookupRestaurantRes, pageable, projections.getTotalElements());
    }

    public ShopCategoryRes getCategoryInShop(Long shopId) {
        return shopRepository.findAllCategoryEntityByShop(shopId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.SHOP_NOT_FOUND));
    }

    public ShopGoodsRes getGoodsInShop(Long shopId, Status status) {
        return shopRepository.findAllGoodsEntityByShopAndStatus(shopId, status)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.GOODS_NOT_FOUND));
    }
}
