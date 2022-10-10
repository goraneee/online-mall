package com.example.mall.entity;

import com.example.mall.entity.enumType.Category;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)       // auto
    private Long id;

    private String productName; // 유일한 값을 줘야되나?
    private Integer price;      // 상품 가격
    private Boolean soldOutYn;  // 품절 여부
    private String productImg;  // 상품 이미지 주소 ??

    @Enumerated(EnumType.STRING)
    private Category category;  // 딱 한개

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    // 재고 관련 사항
    private int stockQuantity;    // 재고 수량 => 주문할 마다 (-)
    private LocalDateTime stockedDt;  // 입고 예정일 => 매월 초, 한 번에 1000 개씩 들어온다 가정

    // 추가 컬럼
    private Integer cumulativeSales;    // 상품별 누적 판매 개수 => 주문할 마다 (+)

}



