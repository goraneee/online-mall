package com.example.mall.entity;

import com.example.mall.entity.Category;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)       // auto
    private Long productId;

    private String productName;
    private Integer price;      // 상품 가격
    private Boolean soldOutYN;  // 품절 여부
    private String productImg;  // 상품 이미지

    //
    @Enumerated(EnumType.STRING)
    private Category category;  // 딱 한개


    // 재고 관련 사항
    private Integer productQuantity;    // 재고 수량
    private LocalDateTime stockedDate;  // 입고 예정일 => 한 번에 1000개씩 들어온다 가정?






}



