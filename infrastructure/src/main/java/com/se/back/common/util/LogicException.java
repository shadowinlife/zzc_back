package com.se.back.common.util;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author mgong
 */
@Getter
public class LogicException extends RuntimeException {
    // http错误码, 参考 https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
    private final @NonNull Integer httpCode;
    private final @NonNull String errorCode;
    private final @NonNull String errorMessage;

    public LogicException(int httpCode, String errorCode, String errorMessage) {
        super(errorMessage);
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public LogicException(int httpCode, String errorCode, Exception e) {
        super(e);
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.errorMessage = e.getMessage();
    }
}
