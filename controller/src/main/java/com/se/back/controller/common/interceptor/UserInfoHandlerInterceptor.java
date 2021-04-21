package com.se.back.controller.common.interceptor;

import com.se.back.common.holder.UserInfo;
import com.se.back.common.holder.UserInfoHolder;
import com.se.back.data.enums.UserType;
import com.se.back.data.repo.redis.mapper.CookieMapper;
import com.se.back.data.repo.redis.repo.CookieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author mgong
 */
@Slf4j
public class UserInfoHandlerInterceptor implements HandlerInterceptor {
    private CookieRepository cookieRepository;

    public UserInfoHandlerInterceptor(CookieRepository cookieRepository) {
        this.cookieRepository = cookieRepository;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {
        String cookie = request.getHeader("jf-auth-cookie");
        UserInfo userInfo;
        if (cookie == null) {
            cookie = UUID.randomUUID().toString();
            userInfo = new UserInfo(cookie, UserType.ANONYMOUS, cookie);
        } else if (!cookieRepository.existsById(cookie)) {
            log.warn("cookie {} invalid", cookie);
            cookie = UUID.randomUUID().toString();
            userInfo = new UserInfo(cookie, UserType.ANONYMOUS, cookie);
        } else {
            CookieMapper cookieBean = cookieRepository.findById(cookie).get();
            userInfo = new UserInfo(cookieBean.getUserIdentity(),
                    UserType.valueOfType(cookieBean.getUserType()), cookie);
        }
        UserInfoHolder.setUserInfo(userInfo);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        UserInfoHolder.reset();
    }
}
