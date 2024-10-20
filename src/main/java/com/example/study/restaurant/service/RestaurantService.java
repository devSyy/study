package com.example.study.restaurant.service;

import com.example.study.config.entity.Status;
import com.example.study.config.response.exception.CustomException;
import com.example.study.config.response.exception.CustomExceptionStatus;
import com.example.study.restaurant.dto.DetailRestaurantRes;
import com.example.study.restaurant.dto.LookupRestaurantRes;
import com.example.study.restaurant.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 코드를 더 간결하고 가독성 좋게 하기 위해서입니다. 상수를 많이 사용하는 상황에서 반복적인 클래스 참조를 줄여주는 역할
import static com.example.study.config.entity.Status.Valid;

@RequiredArgsConstructor
//@Transactional(readOnly = true)
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

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
