package com.se.back.common.interceptor;

import com.se.back.common.holder.RequestIdHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestIdHandlerInterceptor implements HandlerInterceptor {

    private static final String REQUEST_ID_KEY = "requestId";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {
        /**
         * 获取请求header中的requestId, 本次请求的所有日志中都有这个id
         */
        String requestId = request.getHeader(REQUEST_ID_KEY);
        if (StringUtils.isBlank(requestId)) {
            requestId = RequestIdHolder.makeRequestId();
        }
        RequestIdHolder.setRequestId(requestId);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        RequestIdHolder.reset();
    }
}
