package com.example.mall.admin.repository;

import com.example.mall.entity.Order;
import com.example.mall.entity.Product;
import com.example.mall.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


}
