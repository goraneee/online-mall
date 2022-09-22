
package com.example.mall.configuration;


import com.example.mall.repository.MemberRepository;
import com.example.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private  final MemberService memberService;
//    private  final LoginHistoryRepository loginHistoryRepository;
    private  final MemberRepository memberRepository;


    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    UserAuthenticationFailureHandler getFailureHandler(){
//        return  new UserAuthenticationFailureHandler();
//    }

    // 성공 핸들러 추가한다.
//    @Bean
//    UserAuthenticationSuccessHandler getSuccessHandler(){
//        return  new UserAuthenticationSuccessHandler(loginHistoryRepository, memberRepository);
//    }

    @Override
    public   void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/files/**");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();      // csrf: 토큰
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers(
                        "/"
                        , "/member/register"
                        , "/member/email-auth"
                        , "/member/find-password"
//                        , "/member/reset/password"
//                        , "/admin"
                )
                .permitAll();

        http.authorizeRequests()
                        .antMatchers("/admin/**")
                        .hasAnyAuthority("ROLE_ADMIN");

        http.formLogin()
                .loginPage("/member/login")
//                .failureHandler(getFailureHandler())
//                .successHandler(getSuccessHandler())
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")          // 로그아웃 성공하면 메인페이지 이동
                .invalidateHttpSession(true);   // 로그아웃했으니 새션 초기화

        // 추가 - 접근 제한
        http.exceptionHandling()
                .accessDeniedPage("/error/denied");
        super.configure(http);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberService)
//                .passwordEncoder(getPasswordEncoder());
//        super.configure(auth);
//
//
//        auth.userDetailsService(memberService).passwordEncoder()
//    }

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


