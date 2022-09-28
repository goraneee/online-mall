package com.example.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController extends  BaseController {

    @GetMapping("/admin/main.do")
    public String main(){
        return "admin/main";
    }
}