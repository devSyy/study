package com.example.study.goods.service;

import com.example.study.config.response.exception.CustomException;
import com.example.study.config.response.exception.CustomExceptionStatus;
import com.example.study.goods.dto.DetailGoodsRes;
import com.example.study.goods.repository.GoodsRepository;
import com.example.study.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final ShopRepository shopRepository;

    public DetailGoodsRes getDetailGoods(Long id) {
        return goodsRepository.findByGoodsId(id)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.GOODS_NOT_FOUND));
    }

}