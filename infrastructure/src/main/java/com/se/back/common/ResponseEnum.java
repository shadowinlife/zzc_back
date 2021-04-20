package com.se.back.common;

/**
 * 响应枚举
 *
 * @author xin
 * @date 2020/12/05
 */

public enum ResponseEnum {
    /**
     *
     */
    SUCCESS(1, "Success"),
    UNKNOWN_ERROR(2, "未知异常"),
    ERROR(3, "请求失败"),
    SYSTEM_ERROR(4, "系统异常"),
    TIME_OUT_ERROR(5, "ELASTICSEARCH超时异常"),

    PARAMS_ERROR("-1000", "参数错误"),

    USERNAME_OR_PASSWORD_ERROR("-2000", "用户名或密码错误"),
    LOGIN_ERROR("-2001", "session已过期"),
    SESSION_ERROR("-2002", "不允许用户多地登录"),
    USERNAME_ALREADY_EXIST("-2003", "用户已存在"),
    ADMIN_USER_NOT_EXIST("-2004", "管理员用户不存在"),
    USER_NOT_EXIST("-2005", "用户不存在"),
    PHONE_PARAMS_ERROR("-2006", "手机号码错误"),
    PHONE_MESSAGE_CODE_SEND_ERROR("-2007", "手机验证码发送失败, 请重试"),
    FREQUENCY_EXCEEDING_LIMIT("-2007", "今日验证码已发送超过限制次数"),
    PHONE_CODE_ERROR("-2008", "验证码不正确"),
    STAFF_USER_NOT_EXIST("-2009", "标注人员不存在"),
    USER_ERROR_ROLE("-2010", "用户角色不匹配"),

    PROJECT_ALREADY_EXISTS("-3000", "标注工程已存在"),
    PROJECT_NOT_EXISTS("-3001", "标注工程不存在"),
    PROJECT_NOT_HAS_STAFF("-3002", "标注工程没有此标注人员"),
    PROJECT_ALREADY_HAS_STAFF("-3003", "标注工程已有此标注人员"),
    STAFF_DATA_EXPIRED("-3004", "标注工作已超时,任务已释放,请重新获取任务"),
    TASK_HAS_RELEASED("-3005", "任务已被释放,请重新获取任务进行标注"),
    PROJECT_HAS_CLOSED("-3006", "标注工程已关闭"),
    ITEM_NOT_EXISTS("-3007", "标注数据不存在"),
    PROJECT_CLOSED_DO_NOT_RELEASE_TASK("-3008", "标注工程已关闭,不能释放任务"),

    REDIS_DB_ERR("-5000", "缓存服务异常"),
    MYSQL_DB_ERR("-5001", "存储服务异常"),


    SNIFFER_INDEX_NOT_FOUND_ERROR("-3009", "sniffer-index不存在"),
    URL_FORMAT_ERROR("-3010", "url格式错误"),
    COMMON_INDEX_NOT_FOUND_ERROR("-3011", "common-index不存在"),
    ;

    private final Object code;
    private final String message;

    ResponseEnum(Object code, String message) {
        this.code = code;
        this.message = message;
    }

    public Object getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
