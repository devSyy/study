package com.example.study.restaurant.repository;

import com.example.study.config.entity.Status;
import com.example.study.restaurant.dto.DetailRestaurantRes;
import com.example.study.restaurant.dto.LookupRestaurantRes;
import com.example.study.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("select r from Restaurant r where r.restaurantId = :id and r.status != 'Delete'")
    Optional<DetailRestaurantRes> findByRestaurantId(Long id);
    Page<LookupRestaurantRes> findAllByStatusOrderByUpdatedAtDesc(Pageable page, Status status);
    List<LookupRestaurantRes> findAllByStatusAndGeneralAddressAndIsExpressOrderByUpdatedAtDesc(Status status, String generalAddress, Boolean isExpress);

}
