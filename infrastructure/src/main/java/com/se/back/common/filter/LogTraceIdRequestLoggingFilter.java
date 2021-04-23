package com.se.back.common.filter;

import com.se.back.common.holder.RequestIdHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * https://juejin.cn/post/6950772721139580936
 */
public class LogTraceIdRequestLoggingFilter extends AbstractRequestLoggingFilter {
    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String s) {
        String requestId = httpServletRequest.getHeader(RequestIdHolder.LOG_TRACE_ID_KEY);
        if (StringUtils.isBlank(requestId)) {
            requestId = RequestIdHolder.makeLogTraceId();
        }
        RequestIdHolder.setRequestId(requestId);
    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String s) {
        RequestIdHolder.removeRequestId();

    }
}
