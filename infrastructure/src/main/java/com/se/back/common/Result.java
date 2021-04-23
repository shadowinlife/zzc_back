package com.se.back.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.se.back.common.util.JsonUtil;
import lombok.Data;
import lombok.ToString;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author mgong
 */
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private static final String REQUEST_ID = "requestId";

    private static final String ERROR_CODE = "status";

    private static final String ERROR_INFO = "info";

    private static final String DATA = "data";

    private String requestId;

    private Object status;

    private String info;

    private T data;

    public Result(String requestId, Object status, String info, T data) {
        this.requestId = requestId;
        this.status = status;
        this.info = info;
        this.data = data;
    }

    public static <T> Result<T> successResult(String requestId, T data) {
        return new Result<>(requestId, ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> errResult(
            String requestId,
            ResponseEnum responseEnum
    ) {
        return new Result<>(requestId, responseEnum.getCode(), responseEnum.getMessage(), null);
    }

    public static <T> Result<T> errResult(
            String requestId,
            String errorCode,
            String message) {
        return new Result<T>(requestId, errorCode, message, null);
    }

    public static String successResultString(String requestId, Object data) throws JsonProcessingException {
        Result<Object> result = successResult(requestId, data);
        return JsonUtil.makeJson(result);
    }

    public static <T> Result<T> fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        T data = JsonUtil.makeObject(json, clazz);
        return successResult(null, data);
    }

    static <T> ParameterizedType type(final Class<T> raw, final Type... args) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };

    }
}
