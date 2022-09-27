package com.example.mall.entity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
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
public class Coupon {

    @Id  @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "coupon")
    @JoinColumn
    private Member member;

    @Enumerated(EnumType.STRING)
//    String으로 하면 SQL 오류가 난다.   ==> 해결하기
    private CouponType couponType;  // 쿠폰 종류

    private LocalDateTime couponRegDt;  // 쿠폰 등록일
    private LocalDateTime couponExpirationDt; // 쿠폰 만료일

}



