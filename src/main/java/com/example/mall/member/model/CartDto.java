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
public class CartDto {

    private Long id;
    private String name;
    private Member member;
    private List<Product> productList = new ArrayList<>();
}
