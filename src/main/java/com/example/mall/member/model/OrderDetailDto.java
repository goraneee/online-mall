package com.example.mall.member.model;

import com.example.mall.entity.Product;
import com.example.mall.member.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

    private Long id;
    private int quantity;
    private Product product;
    private Order order;

}