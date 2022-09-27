package com.example.mall.controller;

import com.example.mall.entity.Member;
import com.example.mall.model.MemberInput;
import com.example.mall.model.ResetPasswordInput;
import com.example.mall.repository.MemberRepository;
import com.example.mall.service.MemberService;
import com.example.mall.service.MemberServiceImpl;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
                        , Principal principal){

        // 아이디 중복 - 데이터베이스 저장 X
        boolean result = memberService.register(parameter);
        if(!result){
            boolean MatchPassword = parameter.getPassword().equals(parameter.getRePassword());
            if(!MatchPassword){
                return "error/NotMatchPassword";
            }
            return "error/existsId";
        }

        model.addAttribute( "result", result);  //  항상 true로 넘어간다
        model.addAttribute( "detail", parameter);  // model에 넣으면 어떻게 html로 넘어가지??
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
        boolean errorMessage = false;
        if(!memberService.authenticate(memberInput)){
            model.addAttribute("errorMassage", errorMessage);
            return "member/login";
        }
        // 로그인 성공하면
        errorMessage = true;
        model.addAttribute("errorMassage", errorMessage);

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


    // 비밀번호 찾기
    @GetMapping("/member/find-password")
    public String findPassword(){
        return "member/find_password";
    }

    @PostMapping("/member/find-password")
    public String findPasswordSubmit(Model model,
                                    ResetPasswordInput parameter){

        boolean result = false;
        try{
            result = memberService.sendResetPassword(parameter);    // 초기화 메일 보낸 결과

        }catch (Exception e){
            e.printStackTrace();
        }

        model.addAttribute("result", result);
        return "member/find_password_result";
    }












}
