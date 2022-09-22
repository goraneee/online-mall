package com.example.mall.controller;

import com.example.mall.model.MemberInput;
import com.example.mall.service.MemberService;
import com.example.mall.service.MemberServiceImpl;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register(){
        return "member/register";
    }

    @PostMapping("/member/register")
    public String submit(Model model
                        , MemberInput parameter
                        , Principal principal){
        boolean result = memberService.register(parameter);

        // 이미 계정이 있는 경우
        if(!result){
            return "error/existsId";

        }
//        model.addAttribute( "result//, result);  // model에 넣으면 어떻게 html로 넘어가지??
//        model.addAttribute( "detail", parameter);  // model에 넣으면 어떻게 html로 넘어가지??
        return "member/register_complete";
    }

    @GetMapping("/member/login")
    public String login(){
        return "member/login";
    }
}
