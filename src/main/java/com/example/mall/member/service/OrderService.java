package com.example.mall.member.service;

import com.example.mall.member.model.OrderDto;
import com.example.mall.member.model.OrderInput;
import java.util.List;

public interface OrderService {

    /**
     * 주문 신규 추가
     */
    boolean add(OrderInput parameter);   // 인풋으로 바꿔야할 수도 있다.

    /**
     * 상품 수정
     */
    boolean update(OrderInput parameter);


    /**
     * 주문 삭제
     */
    boolean delete(long id); // 기본키

    /**
     * 주문 리스트
     */
    List<OrderDto> list(Long memberId);

    /**
     * 모든 회원의 주문 리스트
     */
    List<OrderDto> list();


}
