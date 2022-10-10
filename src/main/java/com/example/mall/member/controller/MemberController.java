package com.example.mall.member.controller;

import com.example.mall.member.repository.MemberRepository;
import com.example.mall.member.model.MemberInput;
import com.example.mall.repository.model.ResetPasswordInput;
import com.example.mall.member.service.MemberService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/member/register")
    public String register(){
        return "member/register";
    }

    @PostMapping("/member/register")
    public String submit(Model model
                        , MemberInput parameter
                        , Principal principal){             //      User 객체
        // SecurityConfig 에 반환한 new User()에 대한 name 반환 가능
        //

        // 아이디 중복 - 데이터베이스 저장 X
        boolean result = memberService.register(parameter);
        if(!result){
            boolean MatchPassword = parameter.getPassword().equals(parameter.getRePassword());
            if(!MatchPassword){
                return "error/NotMatchPassword";
            }
            return "error/existsId";
        }
        return "member/register_complete";
    }

    @GetMapping("/member/login")
    public String login(HttpServletRequest request,
                        Model model){
        return "member/login";
    }


    @PostMapping("/member/login")
    public String loginSubmit(HttpServletRequest request,
                              Model model,
                              MemberInput memberInput){
        return "index";
    }


    // 이메일 인증
    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request){

        String uuid = request.getParameter("id"); // 주소창에 보이는 변수
        System.out.println(uuid);
        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);   // 현재 결과 result를 html로 result라는 변수로 넘긴다.
        return "member/email-auth";
    }

    @GetMapping("/member/product")
    public String product(){

        return "member/product";
    }


    @GetMapping("/member/order")
    public String order(){

        return "member/order";
    }


    @GetMapping("/member/cart")
    public String cart(){

        return "member/cart";
    }

}
