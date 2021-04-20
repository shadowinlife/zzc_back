package com.se.back.controller.common.config;


import com.se.back.controller.common.interceptor.RequestIdHandlerInterceptor;
import com.se.back.controller.common.interceptor.RequestLoggerHandlerInterceptor;
import com.se.back.controller.common.interceptor.UserInfoHandlerInterceptor;
import com.se.back.data.repo.redis.repo.CookieRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    private final static String EXCLUDE_URL = "/health/*";

    private final CookieRepository cookieRepository;

    public WebConfigurer(CookieRepository cookieRepository) {
        this.cookieRepository = cookieRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestIdHandlerInterceptor());
        registry.addInterceptor(
                new UserInfoHandlerInterceptor(cookieRepository))
                .excludePathPatterns(EXCLUDE_URL);
        registry.addInterceptor(new RequestLoggerHandlerInterceptor());
    }
}
