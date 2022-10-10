package com.example.mall.entity.enumType;

public enum UserGrade {
    // 전체 등급 혜택: 생일 10% 할인 쿠폰
    // (최근 5년 동안의 누적 구매 금액)
    NEWBIE, // 누적 구매 금액:  ~ 20,000, 1% 추가 할인
    ROOKIE, // 누적 구매 금액: ~ 100,000, 2% 추가 할인
    BRONZE, // 누적 구매 금액: ~ 1,000,000, 3% 추가 할인
    SILVER, // 누적 구매 금액: ~ 2,000,000, 4% 추가 할인
    GOLD    // 누적 구매 금액: ~ 5,000,000, 5% 추가 할인
}
