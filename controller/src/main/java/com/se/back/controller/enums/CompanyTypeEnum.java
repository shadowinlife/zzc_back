package com.se.back.controller.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author: 信长华
 * @date: 2021/4/19 14:41
 * @className: CompanyTypeEnum
 * @description: 公司身份的枚举
 * @version: 1.0
 */
public enum CompanyTypeEnum {
    BID_COMPANY(1, "中标公司"),
    OWNER(2, "招标公司"),
    AGENCY(3, "代理公司"),
    ;
    private final Integer code;
    private final String message;

    CompanyTypeEnum(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    /**
     * Jackson转成json的时候自动调用此数值
     *
     * @return
     */

    public Integer getCode() {
        return code;
    }
    @JsonValue
    public String getMessage() {
        return message;
    }
}
