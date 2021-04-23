package com.se.back.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: 信长华
 * @date: 2021/4/20 13:13
 * @className: JsonUtil
 * @description: TODO
 * @version: 1.0
 */
public class JsonUtil {
    private final static ObjectMapper mapper = new ObjectMapper();

    /**
     * 数据转json String
     * @param o
     * @return
     * @throws JsonProcessingException
     */
    public static String makeJson(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }

    /**
     * json String 转 dataclass
     * @param data
     * @param valueType
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T makeObject(String data, Class<T> valueType) throws JsonProcessingException {

        return mapper.readValue(data, valueType);
    }
}
