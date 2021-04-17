package com.se.back.controller;

import com.se.back.controller.interceptor.RequestIdHandlerInterceptor;
import com.se.back.controller.interceptor.RequestLoggerHandlerInterceptor;
import com.se.back.controller.interceptor.UserInfoHandlerInterceptor;
import com.se.back.data.repo.redis.repo.CookieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    private static String EXCLUDE_URL = "/health/*";

    @Autowired
    private CookieRepository cookieRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestIdHandlerInterceptor());
        registry.addInterceptor(
            new UserInfoHandlerInterceptor(cookieRepository))
            .excludePathPatterns(EXCLUDE_URL);
        registry.addInterceptor(new RequestLoggerHandlerInterceptor());
    }
}
