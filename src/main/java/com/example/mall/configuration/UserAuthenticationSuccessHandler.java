package com.example.mall.configuration;

import com.example.mall.entity.LoginHistory;
import com.example.mall.entity.Member;
import com.example.mall.repository.LoginHistoryRepository;
import com.example.mall.repository.MemberRepository;
import com.example.mall.util.RequestUtils;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {       // 로그인 성공

    private final LoginHistoryRepository loginHistoryRepository;
    private final MemberRepository memberRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        // 성공한 계정 아이디
        String userId = authentication.getName();
        System.out.println(userId);

//        String id = memberRepository.detail(str);
//        String id = memberService.detail(userId);
//        MemberDto memberDto = memberService.detail(userId);


        Optional<Member> member = memberRepository.findById(userId);
        Member m = member.get();
        m.setLastLoginTime(LocalDateTime.now());
        memberRepository.save(m);

        log.info(userDetails.getUsername());
        log.info("user agent = " + RequestUtils.getUserAgent(request));     //멤버컨트롤러의
        log.info("user ip = " +  RequestUtils.getClientIp(request));

        // 로그인 성공했으니 현재 시간을 넣어준다.
        LoginHistory loginHistory = LoginHistory.builder()
            .userId(userDetails.getUsername())              // 왜 이름을 넣지 ?? 아이디가 아니라?
            .clientIp(RequestUtils.getClientIp(request))
            .userAgent(RequestUtils.getUserAgent(request))
            .lastLoginTime(LocalDateTime.now())
            .build();
        loginHistoryRepository.save(loginHistory);

        setDefaultTargetUrl("/");   // 로그인 한 다음에 페이지


        super.onAuthenticationSuccess(request, response, authentication);
    }


}
