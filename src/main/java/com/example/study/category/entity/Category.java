package com.example.study.category.entity;

import com.example.study.config.entity.BaseTimeEntity;
import com.example.study.config.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String name;

    @Builder
    public Category(String name, Status status){
        this.name = name;
        this.status = status;
    }

}
