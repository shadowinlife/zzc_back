package com.se.back.common.holder;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * 利用slf4j 的mdc, 给当前请求的每一条日志增加一个唯一的key
 * https://juejin.cn/post/6950772721139580936
 */
public class RequestIdHolder {
    public static final String LOG_TRACE_ID_KEY = "requestId";

    /**
     * 生成 uuid的 日志跟踪log
     *
     * @return uuid
     */
    public static String makeLogTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void setRequestId(String logTraceId) {
        MDC.put(LOG_TRACE_ID_KEY, logTraceId);
    }

    public static String getRequestId() {
        String logTraceId = MDC.get(LOG_TRACE_ID_KEY);
        if (logTraceId == null) {
            return "";
        }
        return logTraceId;
    }

    public static void removeRequestId() {
        MDC.remove(LOG_TRACE_ID_KEY);
    }

    public static void clearRequestId() {
        MDC.clear();
    }
}
