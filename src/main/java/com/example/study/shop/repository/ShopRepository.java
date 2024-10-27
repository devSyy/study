package com.example.study.shop.repository;

import com.example.study.config.entity.Status;
import com.example.study.shop.dto.DetailShopRes;
import com.example.study.shop.dto.LookupShopRes;
import com.example.study.shop.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query("select r from Shop r left join fetch r.goodsRelations m where (r.shopId = :id and r.status != 'Deleted')")
    Optional<DetailShopRes> findByShopId(@Param("id") Long id);
    Page<LookupShopRes> findAllByStatusOrderByUpdatedAtDesc(Pageable page, Status status);
    List<LookupShopRes> findAllByStatusAndGeneralAddressAndIsExpressOrderByUpdatedAtDesc(Status status, String generalAddress, Boolean isExpress);

}
