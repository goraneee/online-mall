package com.example.mall.admin.model;

import com.example.mall.entity.Product;
import com.example.mall.entity.enumType.Category;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    private Long id;    // 상품 테이블의 PK


    // 상품 리스트를 반환할 때 필요한 정보
    private String productName;
    private Integer price;      // 상품 가격
    private Boolean soldOutYn;  // 품절 여부
    private String productImg;  // 상품 이미지 사진?

    @Enumerated(EnumType.STRING)
    private Category category;  // 딱 한개


//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "CART_ID")   // 외래 키 매핑 - CART 테이블의 PK id와 JOIN
//    private Cart cart;

    // 재고 관련 사항
    private Integer productQuantity;    // 재고 수량
    private LocalDateTime stockedDt;  // 입고 예정일 => 한 번에 1000개씩 들어온다 가정?


    // 추가 컬럼
    private Integer cumulativeSales;    // 상품별 누적 판매 개수


    // 페이징 위한 컬럼
    long totalCount;
    long seq;



    // of() 오버로딩
    public static List<ProductDto> of(List<Product> productList){
        if(productList!= null){
            List<ProductDto> productDtoList = new ArrayList<>();
            for(Product product : productList){
                productDtoList.add(of(product));
            }
            return productDtoList;
        }
        return null;
    }

    static ProductDto of(Product parameter){
        return ProductDto.builder()
            .category(parameter.getCategory())
            .cumulativeSales(parameter.getCumulativeSales())
            .price(parameter.getPrice())
            .productImg(parameter.getProductImg())
            .productName(parameter.getProductName())
            .productQuantity(parameter.getProductQuantity())
            .soldOutYn(parameter.getSoldOutYn())
            .stockedDt(parameter.getStockedDt())
            .build();
    }




}
