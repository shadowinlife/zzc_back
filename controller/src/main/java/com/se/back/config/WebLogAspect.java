package com.se.back.config;

import com.se.back.common.util.JsonUtil;
import com.se.back.entity.WebLog;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * 参考
 * https://github.com/xkcoding/spring-boot-demo/blob/master/demo-log-aop/src/main/java/com/xkcoding/log/aop/aspectj/AopLog.java
 * https://gitee.com/macrozheng/mall-learning/blob/master/mall-tiny-aop/src/main/java/com/macro/mall/tiny/component/WebLogAspect.java
 * https://juejin.cn/post/6844903743511592973
 *
 * @author xin
 */
@Aspect
@Component
public class WebLogAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 定义切点Pointcut
     * 第一个*号：表示返回类型， *号表示所有的类型
     * 第二个*号：表示类名，*号表示所有的类
     * 第三个*号：表示方法名，*号表示所有的方法
     * 后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    @Pointcut("execution(public * com.se.back.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
    }

    /**
     * 环绕操作
     *
     * @param joinPoint 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("webLog()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        // 方法开始的时间
        long startTime = System.currentTimeMillis();

        // 方法执行
        Object result = joinPoint.proceed();

        // 方法结束执行时间
        long endTime = System.currentTimeMillis();
        String userAgent = request.getHeader("User-Agent");
        // 请求相关参数
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        // webLog
        final WebLog webLog = new WebLog();
        webLog.setThreadId(Long.toString(Thread.currentThread().getId()));
        webLog.setThreadName(Thread.currentThread().getName());
        webLog.setIp(getIp(request));
        webLog.setUrl(request.getRequestURL().toString());
        webLog.setUri(request.getRequestURI());
        webLog.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()));
        webLog.setHttpMethod(request.getMethod());

        webLog.setRequestParams(getParameter(method, joinPoint.getArgs()));

        // 响应结果
        webLog.setResult(result);
        webLog.setTimeCost(endTime - startTime);
        webLog.setUserAgent(userAgent);

        logger.info("op=RecordRequestAndResponseInfo, webLog = [{}]", JsonUtil.makeJson(webLog));

        return result;
    }


    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }

    private static final String UNKNOWN = "unknown";

    /**
     * 获取ip地址
     */
    public String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return ip;
    }


}