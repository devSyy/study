package com.example.study.restaurant.entity;

import com.example.study.config.entity.BaseTimeEntity;
import com.example.study.config.entity.Status;
import com.example.study.restaurant.dto.LookupRestaurantRes;
import jakarta.persistence.*;
import lombok.*;

//@SqlResultSetMapping(
//        name = "LookupRestaurantResMapping",
//        classes = @ConstructorResult(
//                targetClass = LookupRestaurantRes.class,
//                columns = {
//                        @ColumnResult(name = "restaurantId", type = Long.class),
//                        @ColumnResult(name = "name", type = String.class),
//                        @ColumnResult(name = "grade", type = Float.class)
//                }
//        )
//)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Restaurant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String name;

    private Float grade;

    private String generalAddress;

    private String detailedAddress;

    private Boolean isExpress;
}
