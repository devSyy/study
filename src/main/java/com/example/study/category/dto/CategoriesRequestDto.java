package com.example.study.category.dto;

import com.example.study.category.entity.Category;
import com.example.study.config.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoriesRequestDto {

    private static final long serialVersionUID = 4L;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String name;

    public Category toEntity(){
        return Category.builder()
                .name(name)
                .status(status)
                .build();
    }

}
