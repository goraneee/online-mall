package com.example.mall.member.service;

import com.example.mall.member.model.OrderDetailDto;
import java.util.List;

public interface OrderDetailService {

    List<OrderDetailDto> list(Long orderId);
}
