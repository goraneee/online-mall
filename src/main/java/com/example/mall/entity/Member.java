package com.example.mall.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements MemberCode{

    @Id    @GeneratedValue
    private Long id;
    private String userId;  // 이메일이랑 같음
    private String userName;
    private String phone;
    private String password;
    private String zipcode; // 우편 번호
    private String addr; //
    private String addrDetail;

    private Long point; // 포인트 잔액
    private LocalDateTime regDt;    // 회원 가입일
    private boolean adminYn;    // 관리자 여부 - false 기본값으로 넣기
    private String userStatus;  // REQ / ING / STOP / WITHDRAW

//    private LocalDateTime udtDt;    // 회원 정보 수정일
    private LocalDateTime lastLoginTime;    //마지막 로그인 날짜는  => 이메일 인증관련해서 쓴다.
    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt; //초기화  가능한 날짜


    //   연관 관계
    @OneToOne(cascade = CascadeType.ALL)    // 추가하면 member 테이블이 달라질 때, cart도 달라진다.
    @JoinColumn(name = "CART_ID")   // 카트의 변수명과 달라도되나???
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COUPON_ID")   // 카트의 변수명과 달라도되나???
    private Coupon coupon;


    // 추가사항: 누적 구매액, 회원 등급







}
