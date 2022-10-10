package com.example.mall.member.model;

import com.example.mall.entity.Product;
import com.example.mall.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartInput {

    private Long id;
    private String name;
    private Member member;  // mappedBy를 조인컬럼으로 바꾸면? mamberId  컬럼 생긴다.
    private List<Product> productList = new ArrayList<>();
}
