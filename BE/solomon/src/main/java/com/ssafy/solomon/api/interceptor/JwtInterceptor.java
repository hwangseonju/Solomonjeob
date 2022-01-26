package com.ssafy.solomon.api.interceptor;

import com.ssafy.solomon.api.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        System.out.println(request.getMethod() + " :"+request.getServletPath());

        // option 요청은 바로 통과시켜주자.
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }else{
            // request의 parameter에서 auth-token으로 넘어온 녀석을 찾아본다.
            // String token = request.getParameter("auth-token");
            String token = request.getHeader("jwt-auth-token");
            if(token!=null && token.length()>0){
                securityService.validateToken(token);
                return true;
            }else {
                throw new RuntimeException("인증 토큰이 없습니다.");
            }
        }
    }
}
