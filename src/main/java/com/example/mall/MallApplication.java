package com.example.mall;
import com.example.mall.component.MailComponents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class MallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
//        try {
//            Thread.sleep(3000L);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        MailComponents mailComponents = new MailComponents(new JavaMailSenderImpl());
////        mailComponents.sendMainTest();
//        mailComponents.sendMail("lan4250@naver.com", "제목", "네용");
//

    }
}
