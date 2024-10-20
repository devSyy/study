package com.example.study.config.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 생성자, 생성시간, 수정자, 수정시간을 모든 엔티티에 공통으로 가져가야 하는 상황에서 매핑정보만 상속받는 Superclass
@EntityListeners(AuditingEntityListener.class) //JPA Entity에 Persist, Remove, Update, Load에 대한 event 전과 후에 대한 콜백 메서드를 제공
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}