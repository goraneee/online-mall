
package com.example.mall.configuration;


import com.example.mall.repository.LoginHistoryRepository;
import com.example.mall.repository.MemberRepository;
import com.example.mall.service.MemberService;
import com.example.mall.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    해제하면 순환 참조 문제가 발생한다. => 해결하기
    private  final MemberService memberService;
    private  final LoginHistoryRepository loginHistoryRepository;
    private  final MemberRepository memberRepository;


    // 앱컨피그에도 똑같이 추가한다.
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 로그인 성공
    @Bean
    UserAuthenticationSuccessHandler getSuccessHandler(){
        return new UserAuthenticationSuccessHandler(loginHistoryRepository, memberRepository);
    }

    // 로그인 실패
    @Bean
    UserAuthenticationFailureHandler getFailureHandler(){
        return  new UserAuthenticationFailureHandler();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/files/**");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();      // csrf: 토큰
        http.headers().frameOptions().sameOrigin();

        // 로그인 없이 접속 가능한 url
        http.authorizeRequests()        // 주소에 대한 권한 설정
                .antMatchers(
                        "/"
                        , "/member/register"
                        , "/member/email-auth"
                        , "/member/find-password"
//                        , "/member/reset/password"        // 없어도 될듯
//                        , "/admin"
                )
                .permitAll();

        http.authorizeRequests()
                        .antMatchers("/admin/**")
                        .hasAnyAuthority("ROLE_ADMIN");


        // 로그인
        // 성공 시 / 실패 시 처리를 다르게 설정
        http.formLogin()
                .loginPage("/member/login")
                .successHandler(getSuccessHandler())
                .failureHandler(getFailureHandler())
                .permitAll();

        // 로그아웃
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")          // 로그아웃 성공하면 메인페이지 이동
                .invalidateHttpSession(true);   // 로그아웃했으니 세션을 초기화 ??

        // 추가 - 접근 제한
        http.exceptionHandling()
                .accessDeniedPage("/error/denied");
        super.configure(http);
    }

    // 회원 인증


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncoder());
        super.configure(auth);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring()
                .antMatchers("/static/**")
                .antMatchers("/favicon.ico")
                .antMatchers("/files/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**"));
    }
}


