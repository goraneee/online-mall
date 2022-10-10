package com.example.mall.entity;

import com.example.mall.member.entity.Member;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coupon {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") // 쿠폰이 멤버를 가져올
    private Member member;

    private Long discountAmount; // 할인 금액

    @Enumerated(EnumType.STRING)
    private CouponType couponType;  // 쿠폰 종류

    private LocalDateTime couponRegDt;  // 쿠폰 등록일
    private LocalDateTime couponExpirationDt; // 쿠폰 만료일

}



