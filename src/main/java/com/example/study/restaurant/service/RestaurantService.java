package com.example.study.restaurant.service;

import com.example.study.config.entity.Status;
import com.example.study.config.response.exception.CustomException;
import com.example.study.config.response.exception.CustomExceptionStatus;
import com.example.study.restaurant.dto.DetailRestaurantRes;
import com.example.study.restaurant.dto.LookupRestaurantRes;
import com.example.study.restaurant.dto.LookupRestaurantResDto;
import com.example.study.restaurant.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 코드를 더 간결하고 가독성 좋게 하기 위해서입니다. 상수를 많이 사용하는 상황에서 반복적인 클래스 참조를 줄여주는 역할
import static com.example.study.config.entity.Status.Valid;

@RequiredArgsConstructor
//@Transactional(readOnly = true)
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Cacheable(value = "restaurants", key = "#root.methodName + '-' + #valid", cacheManager = "redisCacheManager", unless = "#result == null || #result.isEmpty()" )
public Page<LookupRestaurantResDto> getAllRestaurants(Pageable pageable, Status valid) {
        Page<LookupRestaurantRes> projections = restaurantRepository.findAllByStatusOrderByUpdatedAtDesc(pageable, Valid);
        List<LookupRestaurantResDto> ListLookupRestaurantRes = projections.stream()
                .map(projection ->
                        new LookupRestaurantResDto(projection.getRestaurantId()
                                , projection.getName()
                                , projection.getGrade()
                ))
                .collect(Collectors.toList());
        return new PageImpl<>(ListLookupRestaurantRes, pageable, projections.getTotalElements());
    }

    @Cacheable(value = "restaurant", key = "#root.methodName + '-' + #id", cacheManager = "redisCacheManager", unless = "#result == null" )
    public DetailRestaurantRes getDetailRestaurant(Long id) {
        DetailRestaurantRes detailRestaurantRes = restaurantRepository.findByRestaurantId(id)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.Restaurant_NOT_FOUND));
        return detailRestaurantRes;
    }

    public List<LookupRestaurantRes> getConditionList(String condition, String address) {
        List<LookupRestaurantRes> restaurants = null;
        if (condition.equals("express"))
            restaurants = restaurantRepository.findAllByStatusAndGeneralAddressAndIsExpressOrderByUpdatedAtDesc(Valid, address, true);
        else throw new CustomException(CustomExceptionStatus.REQUEST_ERROR);


        return restaurants;

    }
}
