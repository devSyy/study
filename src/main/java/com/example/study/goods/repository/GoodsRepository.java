package com.example.study.goods.repository;

import com.example.study.goods.dto.DetailGoodsRes;
import com.example.study.goods.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
    @Query("select m from Goods m where m.goodsId = :id and m.status != 'Deletd'")
    Optional<DetailGoodsRes> findByGoodsId(Long id);
}
