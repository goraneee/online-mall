package com.example.mall.member.model;

import com.example.mall.member.entity.Member;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderInput {       // 주문서에 넣을 정보


    private Long id;
//    private Long totalPrice;
    private LocalDateTime orderDate;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddr;
    private String recipientAddrDetail;
    private String recipientZipcode;
    private Member member;
}
