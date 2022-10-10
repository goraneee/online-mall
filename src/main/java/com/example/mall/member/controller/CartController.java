package com.example.mall.member.controller;

import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductParam;
import com.example.mall.controller.BaseController;
import com.example.mall.member.entity.Member;
import com.example.mall.member.model.MemberInput;
import com.example.mall.member.model.OrderInput;
import com.example.mall.member.repository.MemberRepository;
import com.example.mall.member.service.CartService;
import com.example.mall.member.service.MemberProductService;
import com.example.mall.member.service.MemberService;
import com.example.mall.member.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CartController extends BaseController {


    // 회원 주문
    private final MemberService memberService;
    private final OrderService orderService;
    private final CartService cartService;
    private final MemberProductService productService;

    private final MemberRepository memberRepository;

    // 회원별 장바구니 상세
    @GetMapping("/member/cart/list.do/{id}")        // 회원의  id에 따른 장바구니 내역 보여준도록 한다.
    public String list(Model model, MemberInput memberInput) {

        // cart id를 넣으면 장바구니 정보가 조회된다.
        List<ProductDto> list = new ArrayList<>();

        try {
            Member existMember = memberRepository.findById(memberInput.getCart().getId()).get();
        } catch (NullPointerException e) {
            System.out.println("회원 정보가 존재하지 않습니다.");
            return "redirect:/";
        }

        try {
            list = cartService.list(memberInput.getCart().getId()); // 멤버의 장바구니 번호를 넣는다.

        } catch (Exception e) {
            if (list.size() == 0) {
                System.out.println("장바구니가 비었습니다.");
                return "member/cart/list";
            }
        }
        model.addAttribute("list", list);
        return "member/cart/list";
    }

    // 장바구니에 상품 추가
    @PostMapping("/member/cart/add.do/{id}")    // 상품 번호를 넣으면 장바구니에
    public String add(Model model, ProductParam parameter) {

//        boolean result = orderService.add(parameter);

        return "redirect:/member/cart/list.do";
    }

    // 장바구니에서 삭제
    @PostMapping("/member/cart/delete.do")
    public String del(Model model, OrderInput parameter) {
        boolean result = orderService.delete(parameter.getId());

        return "redirect:/member/cart/list.do";
    }
}