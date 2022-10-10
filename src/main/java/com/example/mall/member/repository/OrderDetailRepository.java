package com.example.mall.member.repository;

import com.example.mall.member.entity.Order;
import com.example.mall.member.entity.OrderDetail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrder(Order order);
    Optional<OrderDetail> findOrderDetailByOrder(Long orderId);
}
