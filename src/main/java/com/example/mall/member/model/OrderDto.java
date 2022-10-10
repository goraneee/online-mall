package com.example.mall.member.model;

import com.example.mall.admin.model.ProductDto;
import com.example.mall.entity.Product;
import com.example.mall.member.entity.Member;
import com.example.mall.member.entity.Order;
import java.time.LocalDateTime;
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
public class OrderDto {          //  각 회원에 대한 주문 리스트 출룍

    private Long id;
    private LocalDateTime orderDate;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddr;
    private String recipientAddrDetail;
    private String recipientZipcode;
    private Member member;

    static OrderDto of(Order parameter){
        return OrderDto.builder()
            .id(parameter.getId())
            .orderDate(parameter.getOrderDate())
            .recipientName(parameter.getRecipientName())
            .recipientPhone(parameter.getRecipientPhone())
            .recipientAddr(parameter.getRecipientAddr())
            .recipientAddrDetail(parameter.getRecipientAddrDetail())
            .recipientZipcode(parameter.getRecipientZipcode())
            .member(parameter.getMember())
            .build();
    }

    public static List<OrderDto> of(List<Order> orderList) {
        if (orderList != null) {
            List<OrderDto> orderDtoList = new ArrayList<>();
            for (Order order : orderList) {
                orderDtoList.add(of(order));
            }
            return orderDtoList;
        }
        return null;
    }
}
