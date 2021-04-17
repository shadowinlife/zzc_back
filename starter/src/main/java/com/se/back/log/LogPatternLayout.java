package com.se.back.log;

import ch.qos.logback.classic.PatternLayout;

public class LogPatternLayout extends PatternLayout {
    static {
        defaultConverterMap.put("userId", UserIdConverter.class.getName());
        defaultConverterMap.put("requestId", RequestIdConverter.class.getName());
    }
}
