package com.example.study.category;

import com.example.study.category.dto.CategoriesRequestDto;
import com.example.study.category.dto.GetCategoryRes;
import com.example.study.category.entity.Category;
import com.example.study.category.repository.CategoryRepository;
import com.example.study.category.repository.CategoryService;
import com.example.study.config.entity.Status;
import com.example.study.config.response.DataResponse;
import com.example.study.config.response.ResponseService;
import com.example.study.shop.dto.LookupShopRes;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/app")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final ResponseService responseService;
    private final CategoryService categoryService;

    @GetMapping(value = "/categories")
    public DataResponse<List<GetCategoryRes>> getCategories() {
        List<GetCategoryRes> categories = categoryRepository.findAllByStatus(Status.Valid);
        return responseService.getDataResponse(categories);
    }

    @PostMapping(value = "/categories")
    public DataResponse<Category> getCategories(@Validated @RequestBody CategoriesRequestDto categoriesRequestDto) {
        Category categories = categoryRepository.save(categoriesRequestDto.toEntity());
        return responseService.getDataResponse(categories);
    }

    @GetMapping(value = "/shops/categories/{categoryId}")
    public DataResponse<List<LookupShopRes>> getShopListByCategory(@PathVariable(name = "categoryId") Long categoryId) {
        List<LookupShopRes> shopList = categoryService.getShopListByCategory(categoryId);
        return responseService.getDataResponse(shopList);
    }

//    @GetMapping(value = "/categories/{categoryId}/goods")
//    public DataResponse<List<LookupGoodsRes>> getGoodsListByCategory(@PathVariable(name = "categoryId") Long categoryId) {
//        List<LookupGoodsRes> goodsList = categoryService.getGoodsListByCategory(categoryId);
//        return responseService.getDataResponse(goodsList);
//    }

//    @GetMapping(value = "/restaurants/categories/{categoryId}")
//    public DataResponse<List<LookupRestaurantRes>> getRestaurantListByCategory(@PathVariable(name = "categoryId") Integer categoryId,
//                                                                               @RequestParam(name = "address") String address) {
//        List<LookupRestaurantRes> restaurantList = categoryService.getRestaurantListByCategory(categoryId, address);
//        return responseService.getDataResponse(restaurantList);
//    }
//
//    @GetMapping(value = "/menus/categories/{categoryId}")
//    public DataResponse<List<LookupMenuRes>> getMenuListByCategory(@PathVariable(name = "categoryId") Integer categoryId,
//                                                                   @RequestParam(name = "address") String address) {
//        List<LookupMenuRes> menuList = categoryService.getMenuListByCategory(categoryId, address);
//        return responseService.getDataResponse(menuList);
//    }
}