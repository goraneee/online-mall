package com.example.mall.admin.controller;

import com.example.mall.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController extends BaseController {

    @GetMapping("/admin/main.do")
    public String main(){
        return "admin/main";
    }
}