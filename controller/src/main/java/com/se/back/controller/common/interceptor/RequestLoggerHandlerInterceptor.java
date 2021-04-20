package com.se.back.controller.common.interceptor;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author mgong
 */
@Slf4j
public class RequestLoggerHandlerInterceptor implements HandlerInterceptor {

    private final String HEALTH_CHECK = "health";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {
        Map<String, String> params = request.getParameterMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> Joiner.on(",").join(e.getValue())));
        // 规避健康检查的日志信息
        if (!request.getRequestURI().contains(HEALTH_CHECK)) {
            log.info("Endpoint:{} Param:{}  Header:{}",
                    request.getRequestURI(),
                    Joiner.on(";").withKeyValueSeparator("=").join(params));
        }
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        String content = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        // 规避健康检查的日志信息
        if (!request.getRequestURI().contains(HEALTH_CHECK)) {
            log.info("Response:{}", content);
        }
    }
}
