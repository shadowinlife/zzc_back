package com.se.back.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.se.back.common.holder.UserInfo;
import com.se.back.common.holder.UserInfoHolder;

public class UserIdConverter extends ClassicConverter {

    private static final String SYSTEM = "system";

    @Override
    public String convert(ILoggingEvent event) {
        UserInfo userInfo = UserInfoHolder.getUserInfo();
        return null == userInfo ? SYSTEM : userInfo.getUserIdentify();
    }

}
