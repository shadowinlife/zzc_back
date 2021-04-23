package com.se.back.log;

import ch.qos.logback.classic.PatternLayout;

/**
 * 日志中的userID用这种方式设置跟springboot 热更新插件冲突, 在开发环境中不会显示相应的id
 */
public class LogPatternLayout extends PatternLayout {
    static {
        defaultConverterMap.put("userId", UserIdConverter.class.getName());
    }
}
