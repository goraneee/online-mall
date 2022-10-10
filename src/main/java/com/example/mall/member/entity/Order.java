package com.example.mall.member.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ORDERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long totalPrice;    // 주문별 총 금액
    private LocalDateTime orderDate;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddr;
    private String recipientAddrDetail;
    private String recipientZipcode;

    // 연관 관계
    @ManyToOne(cascade = CascadeType.ALL)    // order, member 중에서 order를 연관관계의 주인으로 만들려면?
    @JoinColumn

    private Member member;

    // 결제 수단, 쿠폰  포인트 사용 유뮤
}
