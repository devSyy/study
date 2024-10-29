package com.example.study.shop.repository;

import com.example.study.config.entity.Status;
import com.example.study.shop.dto.ShopCategoryRes;
import com.example.study.shop.dto.LookupShopRes;
import com.example.study.shop.dto.ShopGoodsRes;
import com.example.study.shop.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Page<LookupShopRes> findAllByStatusOrderByUpdatedAtDesc(Pageable page, Status status);
    @Query("select r from Shop r left join fetch r.shopCategoryRelations m where (r.shopId = :shopId and r.status != 'Deleted')")
    Optional<ShopCategoryRes> findAllCategoryEntityByShop(Long shopId);
    @Query("select r from Shop r left join fetch r.shopGoodsRelations c where r.shopId = :shopId and r.status = :status")
    Optional<ShopGoodsRes> findAllGoodsEntityByShopAndStatus(Long shopId, Status status);
}
