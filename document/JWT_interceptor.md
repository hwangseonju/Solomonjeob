# JWT Interceptor

- jwtInterceptor
```
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


```

- GlobalReesetExceptionHandler
```
package com.ssafy.solomon.api.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// 인터셉터에서 전달된 예외를 처리할 RestControllerAdvice
@RestControllerAdvice
public class GlobalRestExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> internalServerError(Exception e){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", e.getMessage());
        return resultMap;
    }
}

```

- securityConfig
```
package com.ssafy.solomon.config;

import com.ssafy.solomon.api.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**") // 기본적용경로
                .excludePathPatterns(Arrays.asList("/api/member/**")); // 적용 제외 검토
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        System.out.println("addCorsMappings log");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("jwt-auth-token");
    }
}

```

아직 연동작업 확인이 필요하여 우선 md 파일로 작성