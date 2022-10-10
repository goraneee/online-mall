package com.example.mall.admin.controller;

import com.example.mall.admin.model.ProductInput;
import com.example.mall.controller.BaseController;
import com.example.mall.member.entity.Member;
import com.example.mall.member.model.OrderDto;
import com.example.mall.member.model.OrderInput;
import com.example.mall.member.model.OrderParam;
import com.example.mall.member.repository.MemberRepository;
import com.example.mall.member.service.MemberService;
import com.example.mall.member.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminOrderController extends BaseController {


    private final OrderService orderService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    // 모든 회원의 주문에 대해서
    // 회원은 주문을 수정, 삭제, 추가 가능하지만 관리자는 조회만 가능하다
    @GetMapping("/admin/order/list.do")
    public String list(Model model, OrderParam param){
        param.init();
        List<OrderDto> list = orderService.list();

        long totalCount = 0;
        if(list != null && list.size() > 0){
            totalCount = list.size();
        }
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", list);

        /* Paging
        String queryString = param.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, param.getPageSize(), param.getPageIndex(), queryString);
        model.addAttribute("pager", pagerHtml);
        */
        return  "admin/order/list";
    }

/* 관리자가 주문을 add, delete, update 할 일이 있을지는 모르겠다.
    @PostMapping("/admin/order/add.do")
    public String add(Model model, OrderInput parameter) {

        boolean result = orderService.add(parameter);
        return "redirect:/admin/order/list.do";
    }

    @PostMapping("/admin/order/delete.do")
    public String del(Model model, OrderInput parameter) {

        boolean result = orderService.delete(parameter.getId());
        return "redirect:/admin/product/list.do";
    }

    @PostMapping("/admin/order/update.do")
    public String update(Model model, OrderInput parameter) {
        boolean result = orderService.update(parameter);
        return "redirect:/admin/product/list.do";
    }
*/


}