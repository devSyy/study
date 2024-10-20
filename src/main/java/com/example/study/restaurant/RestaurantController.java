package com.example.study.restaurant;

import com.example.study.config.response.DataResponse;
import com.example.study.config.response.ResponseService;
import com.example.study.restaurant.dto.DetailRestaurantRes;
import com.example.study.restaurant.dto.LookupRestaurantRes;
import com.example.study.restaurant.dto.RestaurantRequestDto;
import com.example.study.restaurant.entity.Restaurant;
import com.example.study.restaurant.repository.RestaurantRepository;
import com.example.study.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static com.example.study.config.entity.Status.*;

import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor //  자동 생성자 주입, final이나 @NonNull으로 선언된 필드만을 파라미터로 받는 생성자를 생성
@RestController
@RequestMapping("/app")
public class RestaurantController {

    private final ResponseService responseService;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    private Logger logger = Logger.getLogger(String.valueOf(RestaurantController.class));

    @GetMapping(value ="/restaurants")
    public DataResponse<Page<LookupRestaurantRes>> getAllRestaurants(@PageableDefault Pageable pageable) {
        Page<LookupRestaurantRes> restaurants = restaurantRepository.findAllByStatusOrderByUpdatedAtDesc(pageable, Valid);
        return responseService.getDataResponse(restaurants);
    }

    @GetMapping(value = "/restaurants/{id}")
    public DataResponse<DetailRestaurantRes> getDetailRestaurant(@PathVariable(name = "id") Long id) {
        DetailRestaurantRes restaurant = restaurantService.getDetailRestaurant(id);
        return responseService.getDataResponse(restaurant);
    }

    @GetMapping(value = "/restaurants/condition/{condition}")
    public DataResponse<List<LookupRestaurantRes>> getConditionList(@PathVariable(name = "condition") String condition,
                                                             @RequestParam(name = "address") String address) {
        List<LookupRestaurantRes> restaurants = restaurantService.getConditionList(condition, address);
        return responseService.getDataResponse(restaurants);
    }

    @PostMapping(value ="/restaurant")
    public void saveRestaurants(@Validated @RequestBody RestaurantRequestDto restaurantRequestDto) {
        restaurantRepository.save(restaurantRequestDto.toEntity());
    }
}