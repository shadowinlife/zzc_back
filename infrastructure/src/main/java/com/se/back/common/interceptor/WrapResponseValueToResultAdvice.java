package com.se.back.common.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.se.back.common.ResponseEnum;
import com.se.back.common.Result;
import com.se.back.common.holder.RequestIdHolder;
import com.se.back.common.util.LogicException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.StreamSupport;


@Slf4j
@ControllerAdvice(WrapResponseValueToResultAdvice.SCAN_PACKAGE)
public class WrapResponseValueToResultAdvice implements ResponseBodyAdvice<Object> {

    static final String SCAN_PACKAGE = "com.se.back.controller";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getMethod().getDeclaringClass().getName().startsWith(SCAN_PACKAGE);
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (o instanceof String) {
            return Result.successResultString(RequestIdHolder.getRequestId(), o);
        }
        return o instanceof Result ? o : Result.successResult(RequestIdHolder.getRequestId(), o);
    }

    /**
     * ????????????
     */
    @ExceptionHandler(LogicException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Result<String> handleBusinessException(LogicException e) {
        log.warn("Logic Exception", e);
        return Result.errResult(RequestIdHolder.getRequestId(), e.getErrorCode(), e.getMessage());

    }

    /**
     * ????????????????????????
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Result<String> handleInvalidException(ConstraintViolationException e) {
        log.error("Input Invalid Content", e);

        // ????????????????????????????????????, ???????????????. ??????????????????, ???????????????ConstraintViolationException???????????????,????????????
        ConstraintViolation<?> violation = e.getConstraintViolations().iterator().next();

        // ??????violation?????????????????????,???????????????????????????????????????
        String paramName = StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                .reduce((first, second) -> second)
                .get()
                .getName();
        // ????????????
        return Result.errResult(RequestIdHolder.getRequestId(), ResponseEnum.PARAMS_ERROR);
    }

    /**
     * json ????????????
     */
    @ExceptionHandler(JsonProcessingException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Result<String> handleInvalidJson(JsonProcessingException e) {
        log.error("Input Invalid Content", e);
        return Result.errResult(RequestIdHolder.getRequestId(), ResponseEnum.PARAMS_ERROR);
    }

    /**
     * ????????????????????????
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Result<String> handleTypeException(MethodArgumentTypeMismatchException e) {
        log.error("Input Invalid Type", e);
        return Result.errResult(RequestIdHolder.getRequestId(), ResponseEnum.PARAMS_ERROR);
    }

    /**
     * ????????????????????????
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Result<String> handleMissParamException(MissingServletRequestParameterException e) {
        log.error("Input Invalid Type", e);
        return Result.errResult(RequestIdHolder.getRequestId(), ResponseEnum.PARAMS_ERROR);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleException(Exception e) {
        log.error("Server Inner Error", e);
        return Result.errResult(RequestIdHolder.getRequestId(),
                ResponseEnum.INTERNAL_SERVER_ERROR);
    }
}
