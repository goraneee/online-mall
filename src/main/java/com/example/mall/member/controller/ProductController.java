package com.example.mall.member.controller;

import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductParam;
import com.example.mall.admin.repository.ProductRepository;
import com.example.mall.admin.service.AdminProductService;
import com.example.mall.controller.BaseController;
import com.example.mall.member.service.MemberProductService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController extends BaseController {


    private final MemberProductService memberProductService;


    private final ProductRepository productRepository;

    @GetMapping("/member/product/list.do")
    public String list(HttpServletRequest request,
                       Model model,
                       ProductParam param) {

        param.init();   // 페이지 초기화
        List<ProductDto> list = memberProductService.list();

        // 검색 타입 데이터 추가하기
        //        param.getSearchType();
        long totalCount = 0;
        if (list != null && list.size() > 0) {
            totalCount = list.size();
        }
        String queryString = param.getQueryString();
        String pagerHtml = getPaperHtml(totalCount,
            param.getPageSize(),
            param.getPageIndex(),
            queryString);
        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "member/product/list";
    }
}