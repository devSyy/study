package com.example.study.goods;

import com.example.study.config.response.DataResponse;
import com.example.study.config.response.ResponseService;
import com.example.study.goods.dto.DetailGoodsRes;
import com.example.study.goods.dto.GoodsRequestDto;
import com.example.study.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app")
public class GoodsController {

    private final GoodsService goodsService;
    private final ResponseService responseService;

    @GetMapping(value = "/goods/{id}")
    public DataResponse<DetailGoodsRes> getDetailMenu(@PathVariable(name = "id") Long id) {
        DetailGoodsRes detailGoods = goodsService.getDetailGoods(id);
        return responseService.getDataResponse(detailGoods);
    }

    @PostMapping(value ="/goods")
    public void saveMenu(@Validated @RequestBody GoodsRequestDto goodsRequestDto) {
        goodsService.save(goodsRequestDto.toEntity());
    }

}