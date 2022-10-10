package com.example.mall.member.repository;

import com.example.mall.member.entity.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    Optional<Order> findById(Long id);
}
