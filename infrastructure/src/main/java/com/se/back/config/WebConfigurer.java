package com.se.back.config;


import com.se.back.common.interceptor.UserInfoHandlerInterceptor;
import com.se.back.data.repo.redis.repo.CookieRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
    @Order(value = -99)
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                new UserInfoHandlerInterceptor(cookieRepository))
                .excludePathPatterns(EXCLUDE_URL);
    }
}
