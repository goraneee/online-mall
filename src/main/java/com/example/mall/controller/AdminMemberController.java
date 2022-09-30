package com.example.mall.controller;

import com.example.mall.model.MemberInput;
import com.example.mall.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminMemberController extends  BaseController {


    private final MemberService memberService;

//    private final LoginHistoryService loginHistoryService;


    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberInput parameter) {        // 파라미터는 필요 없을 것이다.

        parameter.init();   // 유효한 값이 되도록 만든다.
        List<MemberInput> members = memberService.list(parameter);    // 값이 저장되면 토탈카운트가 존재한다.

        long totalCount = 0;
        if (members != null && members.size() > 0) {
            totalCount = members.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(),
            parameter.getPageIndex(), queryString);

        model.addAttribute("list", members);
        model.addAttribute("totalCount", totalCount);   // 데이터를 반환
        model.addAttribute("pager", pagerHtml);   // 데이터를 반환
        return "admin/member/list";
    }



}