package com.example.mall.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {

    @Id  @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "cart")
    @JoinColumn
    private Member member;  // mappedBy를 조인컬럼으로 바꾸면? mamberId  컬럼 생긴다.


    // 오류난다.
//    @OneToMany
//    private List<Product> productList;  // 카트 안에 있는 상품 리스트 - 크기가 상품 개수


    // 상품 총 가격은?
    // 상품이 같은 상품이 여러 개 있을 수도 있으니 Map으로 해야되는지도 확인

//    @OneToMany
//    private Map<Product, Integer> productIntegerMap;


}
