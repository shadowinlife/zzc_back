package com.se.back.config;

import com.se.back.common.filter.LogTraceIdRequestLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当前请求的所有日志添加一个唯一id, 用于日志追踪
 * https://juejin.cn/post/6950772721139580936
 */
@Configuration
public class LogTraceIdConfig {
    @Bean
    public LogTraceIdRequestLoggingFilter logTraceIdRequestLoggingFilter() {
        return new LogTraceIdRequestLoggingFilter();
    }
}
