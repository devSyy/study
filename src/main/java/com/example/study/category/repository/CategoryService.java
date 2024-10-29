package com.example.study.category.repository;

import com.example.study.category.entity.Category;
import com.example.study.config.response.exception.CustomException;
import com.example.study.config.response.exception.CustomExceptionStatus;
import com.example.study.goods.dto.LookupGoodsRes;
import com.example.study.shop.dto.LookupShopRes;
import com.example.study.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;

    public List<LookupGoodsRes> getGoodsListByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_CATEGORY));

        List<LookupGoodsRes> goodsList = new ArrayList<>();

//        goodsList.add(new LookupGoodsRes())


        return goodsList;
    }

    public List<LookupShopRes> getShopListByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_CATEGORY));

//        List<LookupShopRes> shopList = shopRepository.findAllEntityByCategoryAndStatus(categoryId, Status.Valid);
        List<LookupShopRes> shopList = new ArrayList<>();

        return shopList;
    }
}
