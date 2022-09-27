package com.example.mall.configuration;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    // 로그인에 실패한 경우
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String msg = "로그인에 실패했습니다.";
        if(exception instanceof InternalAuthenticationServiceException){
            msg = exception.getMessage();
        }
        // 에러 발생한 경우
        setUseForward(true);
        setDefaultFailureUrl("/member/login?error=true");       // 이 부분 URL은 멤버 컨트롤러에 추가
        request.setAttribute("errorMessage", msg);   // html 파일에다가 "errorMessage"를 전달한다.
//        System.out.println("로그인에 실패했습니다.");
        super.onAuthenticationFailure(request, response, exception);
    }
}
