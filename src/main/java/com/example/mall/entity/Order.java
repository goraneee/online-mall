package com.example.mall.entity;

import com.example.mall.member.entity.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ORDER_ID")
    private Long id;

    private Long totalPrice;    // 주문별 총 금액 - 삭제할지 말지 결정하기

    private LocalDateTime orderDate; //주문일


    // 연관 관계
    @ManyToOne(cascade = CascadeType.ALL)    // order, member 중에서 order를 연관관계의 주인으로 만들려면?
    private Member member;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList = new ArrayList<>();  // 초기화를 꼭 해줘야헀던거 같은데??

    private String recipientName;
    private String recipientPhone;
    private String recipientAddr;
    private String recipientAddrDetail;
    private String recipientZipcode;

}
