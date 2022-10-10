package com.example.mall.admin.controller;

import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductInput;
import com.example.mall.admin.model.ProductParam;
import com.example.mall.admin.repository.ProductRepository;
import com.example.mall.admin.service.AdminProductService;
import com.example.mall.controller.BaseController;
import com.example.mall.entity.Product;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminProductController extends BaseController {

    private final AdminProductService adminProductService;
    private final ProductRepository productRepository;

    @GetMapping("/admin/product/list.do")
    public String list(HttpServletRequest request,
        Model model,
        ProductParam param) {

        param.init();   // 페이지 초기화
        List<ProductDto> list = adminProductService.list();

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
        return "admin/product/list";
    }

    /* searchValue, searchType 상품 검색
    @GetMapping("/admin/product/list.do")
    public String searchList(HttpServletRequest request,
        Model model,
        ProductParam param) {
    }
     */

    @GetMapping(value = {"/admin/product/add.do", "/admin/product/edit.do/{id}"})
    public String add(HttpServletRequest request,
        Model model,
        ProductInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit.do");
        ProductDto detail = new ProductDto();

        // repository에 존재하지 않는 id의 경우 예외 처리를 한다.
        if (editMode) {
            long id = parameter.getId();
            Product existProduct = productRepository.findById(id).get();
            ProductDto productDto = ProductDto.of(existProduct);

            if (existProduct == null) {
//                model.addAttribute("message", "수정할 상품 정보가 없습니다.");
                System.out.println("수정할 상품 정보가 없습니다.");
                return "admin/product/list";
            }
            detail = productDto;
        }
        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);
        return "admin/product/add";
    }

    // 상품 등록 및 수정
    @PostMapping(value = {"/admin/product/add.do", "/admin/product/edit.do/{id}"})
    public String addSubmit(Model model,
        ProductInput parameter,
        Principal principal,
        HttpServletRequest request) {

        boolean editMode = request.getRequestURI().contains("/edit.do");
        boolean result = false;

        if (!productRepository.existsById(parameter.getId())) {
            System.out.println("존재하지 않는 상품 번호입니다.");
            return "admin/product/list";
        }

        // edit 모드
        if (editMode) {
            long id = parameter.getId();
            Product existProduct = productRepository.findById(id).get();
            ProductDto productDto = ProductDto.of(existProduct);
            adminProductService.update(parameter);

            if (existProduct == null) {
                model.addAttribute("message", "수정할 상품 정보가 없습니다.");
                result = false;
                model.addAttribute("result", result);
                model.addAttribute("detail", parameter);
                return "admin/product/list";
            }
        } else {      // 상품 추가
            result = adminProductService.add(parameter);
        }
        model.addAttribute("result", result);
        model.addAttribute("detail", parameter);
        return "redirect:/admin/product/list.do";
    }

    @PostMapping("/admin/product/delete.do")
    public String del(Model model, ProductInput parameter) {
        boolean result = adminProductService.delete(parameter.getId());
        return "redirect:/admin/product/list.do";
    }

}