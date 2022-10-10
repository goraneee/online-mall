package com.example.mall.member.service;

import com.example.mall.member.entity.Order;
import com.example.mall.member.entity.OrderDetail;
import com.example.mall.member.model.MemberParam;
import com.example.mall.member.model.OrderDetailDto;
import com.example.mall.member.model.OrderDto;
import com.example.mall.member.model.OrderInput;
import com.example.mall.member.repository.OrderDetailRepository;
import com.example.mall.member.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public boolean add(OrderInput parameter) {

        Order order = Order.builder()
            .orderDate(parameter.getOrderDate())
            .recipientName(parameter.getRecipientName())
            .recipientPhone(parameter.getRecipientPhone())
            .recipientAddr(parameter.getRecipientAddr())
            .recipientAddrDetail(parameter.getRecipientAddrDetail())
            .recipientZipcode(parameter.getRecipientZipcode())
            .member(parameter.getMember())
            .build();
        orderRepository.save(order);    // save 할 때 자동으로 기본키 값이 만들어진다.
        return true;
    }

    @Override
    public boolean update(OrderInput parameter) {
        Optional<Order> optionalOrder = orderRepository.findById(parameter.getId());

        if(!optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setOrderDate(parameter.getOrderDate());
            order.setMember(parameter.getMember());
            order.setRecipientName(parameter.getRecipientName());
            order.setRecipientPhone(parameter.getRecipientPhone());
            order.setRecipientAddr(parameter.getRecipientAddr());
            order.setRecipientAddrDetail(parameter.getRecipientAddrDetail());
            order.setRecipientZipcode(parameter.getRecipientZipcode());
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        orderRepository.delete(optionalOrder.get());
        return true;
    }

    // 각 회원에 대한 주문 리스트 반환
    /*
    @Override
    public List<OrderDto> list(Long memberId) {
        List<Order> list = orderRepository.findByMember(memberId);
        return OrderDto.of(list);
    }
    */

    // 각 회원에 대한 주문 리스트 반환
    @Override
    public List<OrderDto> list() {
        List<Order> list = orderRepository.findAll();
        return OrderDto.of(list);
    }

    @Override
    public List<OrderDto> list(Long memberId) {
        return null;
    }
}
