package com.example.mall.member.model;

import static javax.persistence.FetchType.LAZY;

import com.example.mall.entity.Cart;
import com.example.mall.entity.Coupon;
import com.example.mall.entity.enumType.UserGrade;
import com.example.mall.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {


    private Long id;

    private String userId;  // 이메일이랑 같음
    private String userName;
    private String phone;
    private Long point; // 포인트 잔액
    private String userStatus;  // 가입 상황 - REQ / ING / STOP / WITHDRAW

    // 추가사항: 누적 구매액, 회원 등급
    @Enumerated(EnumType.STRING)
    private UserGrade userGrade;
    private Long cumulativePurchases;   // 누적 구매액 => 회원 등급을 결정

    //   연관 관계
    @OneToOne(cascade = CascadeType.ALL, fetch = LAZY)    // 추가하면 member 테이블이 달라질 때, cart도 달라진다.
    @JoinColumn(name = "CART_ID")   // 카트의 변수명과 달라도되나???
    private Cart cart;
    private List<Coupon> couponList;

    // 페이징 위한 컬럼
    long totalCount;
    long seq;

    // 관리자가 회원을 조회하는 경우 필요한 정보들
    // 주소는 보류
    static MemberDto of(Member param) {
        return MemberDto.builder()
            .id(param.getId())
            .userId(param.getUserId())
            .userName(param.getUserName())
            .phone(param.getPhone())
            .point(param.getPoint())
            .userStatus(param.getUserStatus())
            .userGrade(param.getUserGrade())
            .cumulativePurchases(param.getCumulativePurchases())
            .cart(param.getCart())
            .build();
    }

    public static List<MemberDto> of(List<Member> members) {
        List<MemberDto> memberDtoList = new ArrayList<>();
        for (Member member : members) {
            memberDtoList.add(MemberDto.of(member));
        }
        return memberDtoList;
    }
}
