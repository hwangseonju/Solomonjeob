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
        //System.out.println("addCorsMappings log");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("jwt-auth-token");
    }
}
