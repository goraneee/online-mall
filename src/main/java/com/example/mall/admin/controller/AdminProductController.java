package com.example.mall.admin.controller;

import com.example.mall.admin.service.ProductService;
import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductInput;
import com.example.mall.admin.model.ProductParam;
import com.example.mall.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminProductController extends  BaseController {


    private final ProductService productService;
    private final MemberService memberService;


    @GetMapping("/admin/product/list.do")
    public String list(Model model,
                        ProductParam param){


        param.init();   // 페이지 초기화
        List<ProductDto> list = productService.list();

        long totalCount = 0;
        if(list  != null && list.size() > 0){
            totalCount = productService.list().size();
        }
        String queryString = param.getQueryString();
        String pagerHtml = getPaperHtml(totalCount,
                                        param.getPageSize(),
                                        param.getPageIndex(),
                                        queryString);
        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        model.addAttribute("list", list);
        return "admin/product/list";
    }

    @PostMapping("/admin/product/add.do")
    public String add(Model model, ProductInput parameter){

        boolean result = productService.add(parameter);
        return "redirect:/admin/product/list.do";
    }

    @PostMapping("/admin/product/delete.do")
    public String del(Model model,ProductInput parameter){
        boolean result = productService.delete(parameter.getId());
        return "redirect:/admin/product/list.do";
    }


    @PostMapping("/admin/product/update.do")
    public String update(Model model, ProductInput parameter){

        boolean result = productService.update(parameter);
        return "redirect:/admin/product/list.do";
    }

}