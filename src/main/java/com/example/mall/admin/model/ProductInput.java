package com.example.mall.admin.model;


import com.example.mall.entity.enumType.Category;
import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInput {

    // 상품 수정하기 위한 매개 변수, 입력값
    private Long id;    // 상품 테이블의 PK - productId
    private String productName;
    private Integer price;
    //    private Boolean soldOutYn;
    private String productImg;

    @Enumerated(EnumType.STRING)
    private Category category;

    // 재고 관련 사항
    private int stockQuantity;    // 재고 수량 => 주문할 마다 (-)
    private LocalDateTime stockedDt;  // 입고 예정일
}
