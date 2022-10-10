package com.example.mall.member.service;

import com.example.mall.member.entity.Order;
import com.example.mall.member.model.OrderDetailDto;
import com.example.mall.member.repository.OrderDetailRepository;
import com.example.mall.member.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    // 하나의 주문에 대해 orderDetailRepository에 여러 개의 디테일이 추가되어야 한다.
    // 하나의 상품을 주문하면 row는 하나이다.


    // 하나의 주문에 포함된 모든 상품과 개수 관련 정보를 리스트로 반환하는 메서드
    @Override
    public List<OrderDetailDto> list(Long orderId) {

        List<OrderDetailDto> list = new ArrayList<>();
        Order order = orderRepository.findById(orderId).get();
        Long orderNo = order.getId();
        orderDetailRepository.findByOrder(order);
        return null;
    }

}
