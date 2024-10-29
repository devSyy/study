package com.example.study.category.repository;

import com.example.study.category.dto.GetCategoryRes;
import com.example.study.category.entity.Category;
import com.example.study.config.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<GetCategoryRes> findAllByStatus(Status Valid);

}
