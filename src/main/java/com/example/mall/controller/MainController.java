package com.example.mall.controller;
import com.example.mall.component.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final MailComponents mailComponents;

    @GetMapping("/")
    public String index(){
        return  "index";
    }


//    @RequestMapping("/")// 요청에 대해 매핑한다.
//    public String index() {   // 메서드가 주소에 대해 매핑한다.
//
//        mailComponents.sendMainTest();;
//        return "index";    // 리턴되는 문자열은 파일명이라고 약속한 것과 같다.
//    }


}
