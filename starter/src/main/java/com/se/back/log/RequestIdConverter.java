package com.se.back.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.se.back.common.holder.RequestIdHolder;

public class RequestIdConverter extends ClassicConverter {

    private static final String INTERNAL = "internal";

    @Override
    public String convert(ILoggingEvent event) {
        return null == RequestIdHolder.getRequestId() ? INTERNAL : RequestIdHolder.getRequestId();
    }

}
