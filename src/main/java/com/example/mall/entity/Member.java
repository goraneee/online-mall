package com.example.mall.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // IDENTITY로 하면 오류나는 이유??
    private Long id;

    private String userId;
    private String userName;
    private String phone;

    private String password;
    private String rePassword;
    private Long point; // 포인트 잔액

    private LocalDateTime regDt;    // 회원 가입일

    private boolean adminYn;    // 관리자 여부 - false 기본값으로 넣기
    private String userStatus;  // 이용 가능 / 정지

    @ElementCollection(fetch = FetchType.EAGER) // 컬렉션이니까 꼭 필요!!
    private List<Coupon> couponList;

//    private LocalDateTime udtDt;    // 회원 정보 수정일

    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt; //초기화 ????  가능한 날짜

//    주소
    private String zipcode; // 우편 번호
    private String addr; //
    private String addrDetail; //
}
