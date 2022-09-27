package com.example.mall.repository;
import com.example.mall.entity.Cart;
import com.example.mall.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {


}
