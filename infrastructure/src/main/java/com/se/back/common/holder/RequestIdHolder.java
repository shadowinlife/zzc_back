package com.se.back.common.holder;

/**
 * @author mgong
 */
public class RequestIdHolder {

    private static ThreadLocal<String> REQUEST_ID_HOLDER = new ThreadLocal<>();

    public static String getRequestId() {
        return REQUEST_ID_HOLDER.get();
    }

    public static void setRequestId(String requestId) {
        REQUEST_ID_HOLDER.set(requestId);
    }

    public static void reset() {
        REQUEST_ID_HOLDER.remove();
    }
}
