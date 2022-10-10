package com.example.mall.admin.controller;

import com.example.mall.controller.BaseController;
import java.net.http.HttpRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController extends BaseController {

    @GetMapping("/admin/main.do")
    public String main() {

        // 관리자가 아닌 경우, 예외처리한다.
        return "admin/main";
    }
}