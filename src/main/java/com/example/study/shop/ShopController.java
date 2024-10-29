package com.example.study.shop;

import com.example.study.config.response.DataResponse;
import com.example.study.config.response.ResponseService;
import com.example.study.shop.dto.*;
import com.example.study.shop.repository.ShopRepository;
import com.example.study.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static com.example.study.config.entity.Status.*;

import java.util.logging.Logger;

@RequiredArgsConstructor //  자동 생성자 주입, final이나 @NonNull으로 선언된 필드만을 파라미터로 받는 생성자를 생성
@RestController
@RequestMapping("/app")
public class ShopController {

    private final ResponseService responseService;
    private final ShopRepository shopRepository;
    private final ShopService shopService;

    private Logger logger = Logger.getLogger(String.valueOf(ShopController.class));

    @GetMapping(value = "/shops")
    public DataResponse<Page<LookupShopResDto>> getAllShops(@PageableDefault Pageable pageable) {
        Page<LookupShopResDto> shops = shopService.getAllShops(pageable, Valid);
        return responseService.getDataResponse(shops);
    }

    @GetMapping(value = "/shops/category/{shopId}")
    public DataResponse<ShopCategoryRes> getCategoryInShop(@PathVariable(name = "shopId") Long shopId) {
        ShopCategoryRes shops = shopService.getCategoryInShop(shopId);
        return responseService.getDataResponse(shops);
    }

    @GetMapping(value = "/shops/goods/{shopId}")
    public DataResponse<ShopGoodsRes> getGoodsInShop(@PathVariable(name = "shopId") Long shopId) {
        ShopGoodsRes goods = shopService.getGoodsInShop(shopId, Valid);
        return responseService.getDataResponse(goods);
    }

    @PostMapping(value ="/shop")
    public void saveShops(@Validated @RequestBody ShopRequestDto shopRequestDto) {
        shopRepository.save(shopRequestDto.toEntity());
    }
}