package com.example.mall.admin.model;


import static javax.persistence.FetchType.LAZY;

import com.example.mall.admin.model.CommonParam;
import com.example.mall.entity.enumType.Category;
import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInput{


    // 상품 수정하기 위한 매개 변수, 입력값

    private Long id;    // 상품 테이블의 PK

    private String productName;
    private Integer price;
//    private Boolean soldOutYn;
    private String productImg;

    @Enumerated(EnumType.STRING)
    private Category category;


// 카트를 입력할 일은 없음
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "CART_ID")   // 외래 키 매핑 - CART 테이블의 PK id와 JOIN
//    private Cart cart;


    // 재고 관련 사항
    private Integer productQuantity;    // 재고 수량
    private LocalDateTime stockedDt;  // 입고 예정일 => 한 번에 1000개씩 들어온다 가정?


    // 누적 판매개수는 여기서 처리할 게 아니겠지?



}
