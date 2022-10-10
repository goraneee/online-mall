package com.example.mall.component;

import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailComponents {


    private final JavaMailSender javaMailSender;

    public void sendMainTest() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("lan4250@naver.com"); // 받는 사람
        msg.setSubject("안녕하세요");    // 제목
        msg.setText("안녕하세요 제로베이스입니다. 반갑습니다.");
        javaMailSender.send(msg);
    }


    public boolean sendMail(String mail, String subject, String text) {

        boolean result = false;

        try {

            MimeMessagePreparator msg = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true,
                        "UTF-8");
                    mimeMessageHelper.setTo(mail);
                    mimeMessageHelper.setSubject(subject);
                    mimeMessageHelper.setText(text, true);
                }
            };

            javaMailSender.send(msg);
            result = true;  // 메일 전송에 성공하면 true

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

}

