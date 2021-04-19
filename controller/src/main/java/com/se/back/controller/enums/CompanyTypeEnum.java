package com.se.back.controller.enums;

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

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
