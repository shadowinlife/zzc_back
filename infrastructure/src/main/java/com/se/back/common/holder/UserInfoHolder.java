package com.se.back.common.holder;

import com.se.back.data.enums.UserType;


/**
 * @author mgong
 */
public class UserInfoHolder {

    private static ThreadLocal<UserInfo> USER_INFO = new ThreadLocal<>();

    public static UserInfo getUserInfo() {
        return USER_INFO.get();
    }

    public static void setUserInfo(UserInfo userInfo) {
        USER_INFO.set(userInfo);
    }

    public static void reset() {
        USER_INFO.remove();
    }

    public static String getUserIdentify() {
        return getUserInfo().getUserIdentify();
    }

    public static UserType getUserType() {
        return getUserInfo().getUserType();
    }

    public static String getUserCookie() {
        return getUserInfo().getCookie();
    }

}
