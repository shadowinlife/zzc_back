package com.se.back.controller.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: 信长华
 * @date: 2021/4/20 13:36
 * @className: ElasticSearchConstant
 * @description: TODO
 * @version: 1.0
 */
public class ElasticSearchConstant {
    public final static String SNIFFER_PROVINCE_INDEX_NAME = "sniffer-province-v5";
    public final static String SNIFFER_CITY_INDEX_NAME = "sniffer-city-v5";
    public final static String SNIFFER_COUNTY_INDEX_NAME = "sniffer-county-v5";
    public final static Set<String> WORD_SET = ElasticSearchConstant.makeStopWordSet();

    static Set<String> makeStopWordSet() {

        String[] charSting = new String[]{
                "】",
                "、",
                "<",
                "］",
                "：",
                "&",
                ";",
                "—",
                "●",
                "！",
                "\\",
                "“",
                "?",
                "－",
                "*",
                ":",
                "？",
                "'",
                "!",
                "）",
                "=",
                "》",
                "【",
                ">",
                "/",
                "#",
                "]",
                "{",
                "，",
                ".",
                "-",
                "［",
                ")",
                "}",
                "(",
                "~",
                "·",
                "+",
                "`",
                "；",
                "$",
                "”",
                "_",
                "\"",
                "%",
                "@",
                "|",
                "《",
                "。",
                "[",
                "（",
                ",",
                "^",
        };

        return new HashSet<>(Arrays.asList(charSting));
    }
}
