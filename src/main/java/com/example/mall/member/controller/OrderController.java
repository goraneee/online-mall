package com.example.mall.member.controller;

import com.example.mall.admin.model.ProductInput;
import com.example.mall.controller.BaseController;
import com.example.mall.member.model.OrderDto;
import com.example.mall.member.model.OrderInput;
import com.example.mall.member.model.OrderParam;
import com.example.mall.member.service.MemberService;
import com.example.mall.member.service.OrderService;
import java.net.http.HttpRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController extends BaseController {


    // 회원 주문
      private final MemberService memberService;
      private final OrderService orderService;

    // 회원별 주문 상세
    @GetMapping("/member/order/list.do/{id}")
    public String list(Model model,
                        OrderInput param) {
       List<OrderDto> list = orderService.list(param.getMember().getId());
       model.addAttribute("list",  list);
        return "member/order/list";
    }

    // 주문 추가
    @PostMapping("/member/order/add")
    public String add(Model model, OrderInput parameter, HttpRequest request) {

        boolean result = orderService.add(parameter);
        return "redirect:/member/order/list.do";
    }

    // 주문 취소
    @PostMapping("/member/order/delete.do")
    public String del(Model model, OrderInput parameter) {
        boolean result = orderService.delete(parameter.getId());
        return "redirect:/member/order/list.do";
    }

    // 주문 수정
    @PostMapping("/member/order/update.do")
    public String update(Model model, ProductInput parameter) {
        return "redirect:/member/order/list.do";
    }
}