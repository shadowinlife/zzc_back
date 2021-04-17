package com.se.back.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author mgong
 */
@Getter
@ToString
public class Result<T> {

    private static final String REQUEST_ID = "requestId";

    private static final String ERROR_CODE = "errorCode";

    private static final String ERROR_INFO = "errorInfo";

    private static final String DATA = "data";

    private String requestId;

    private String errorCode;

    private String errorInfo;

    private T data;

    public Result(
            String requestId,
            String errorCode,
            String errorInfo,
            T data) {
        this.requestId = requestId;
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
        this.data = data;
    }

    public static <T> Result<T> succResult(String requestId, T data) {
        return new Result<T>(requestId, null, null, data);
    }

    public static <T> Result<T> errResult(
            String requestId,
            String errorCode,
            String message) {
        return new Result<T>(requestId, errorCode, message, null);
    }

    public static String succResultString(String requestId, Object data) {
        Result<Object> result = new Result<>(requestId, null, null, data);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(REQUEST_ID, result.getRequestId());
        jsonObject.addProperty(ERROR_CODE, result.getErrorCode());
        jsonObject.addProperty(ERROR_INFO, result.getErrorInfo());
        jsonObject.addProperty(DATA, result.getData().toString());
        return jsonObject.toString();
    }

    public static Result fromJson(String json, Class clazz) {
        Type objectType = type(Result.class, clazz);
        Gson gson = new Gson();
        return gson.fromJson(json, objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
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
