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
    PARAMS_ERROR("400", "参数错误"),
    UNAUTHORIZED("401", "未经授权"),
    FORBIDDEN("403", "没有权限"),
    NOT_FOUND("404", "接口不存在"),
    METHOD_NOT_ALLOWED("405", "接口方法不存在"),
    INTERNAL_SERVER_ERROR("500", "服务器内部异常"),
    FREQUENCY_EXCEEDING_LIMIT("-11001", "验证码发送过于频繁"),
    PHONE_HAS_BEEN_REGISTERED("-11002", "手机号码已经被注册"),
    TOKEN_ERROR("-11003", "验证码错误"),
    PASSWORD_MUST_CONTAINS_LETTER_AND_NUMBER("-11004", "密码必须包含英文子母和数字"),
    TOKEN_IS_OVERTIME("-11005", "验证码已过期，请重新获取新的验证码"),
    USER_ALREADY_EXIST("-11006", "您已注册为网站用户，请登录使用本网站"),
    USER_NOT_EXIST("-11007", "该用户不存在"),
    SESSION_EXPIRED("-11008", "登录已超时,请重新登录"),
    MULTIPLE_USER_LOGIN("-11009", "您的账户已在其他地点登录"),
    USER_NEED_LOGIN("-11010", "未登录用户无此权限"),
    USER_NEED_VIP("-11011", "非会员用户无此权限"),
    PHONE_NEED_REGISTER("-11012", "该手机号未注册为系统用户，请前往注册"),
    EMAIL_OR_CODE_ERROR("-11013", "邮箱或验证码异常"),
    PASSWORD_ERROR("-11014", "密码错误"),
    OPENID_ERROR("-11015", "code已过期,请重新扫码登录"),
    USERNAME_HAS_BEEN_REGISTERED("-11016", "用户名已经被注册"),
    PHONE_OR_PASSWORD_ERROR("-11017", "手机号或密码错误"),
    USER_NEED_BIND_PHONE("-11018", "用户名需要绑定手机号，激活账号"),
    USERNAME_NEED_REGISTER("-11019", "未找到该用户，请前往注册"),
    NOT_PERMISSION_LOGIN("-11020", "非版本用户,禁止登录"),
    NOT_ADMIN_PERMISSION("-11021", "非管理员用户"),
    NOT_ALLOW_REGISTER("-11022", "不允许注册"),
    ORDER_NOT_EXIST("-12001", "订单不存在"),
    DOWNLOAD_ORDER_NOT_APPEAL("-12002", "数据下载订单不支持申诉,如有问题,请提工单"),
    ORDER_HAS_APPEAL("-12003", "订单已经申诉,如果问题未解决,请提工单"),
    HAS_PAID("-12004", "此次支付包含已支付订单"),
    ORDER_HAS_BEEN_PURCHASED("-12005", "该咨询已经下单或购买"),
    PAY_NOT_EXIST("-12006", "支付ID不存在"),
    HAS_TO_PAY("-12007", "此次开发票包含未支付订单"),
    HAS_INVOICED("-12008", "此次开发票包含已开发票订单"),
    CONTENT_IS_EXISTS("-12009", "下载内容已存在，请重新为下载内容命名"),
    ORDER_NEED_TO_PAY("-12010", "需要先支付下载数据的订单"),
    PAID_ORDER_NOT_APPEAL("-12011", "支付成功的订单不支持申诉,如有问题,请提工单"),
    PAID_ONE_WEEK_ORDER("-12012", "已支付过体验版周会员,请开通其他版本会员"),
    NEED_PHONE_NUM_OR_EMAIL("-13001", "未登录用户需要填写手机号码或邮箱"),
    TICKET_NOT_EXIST("-13002", "工单不存在"),
    PAGE_NOT_EXIST("-14001", "订阅信息不存在"),
    PAGE_NAME_EXIST("-14002", "订阅名已存在"),
    SAVE_PAGE_NEED_PAY("-14003", "保存订阅需付费"),
    OVER_SAVE_PAGE_COUNT("-14004", "保存订阅数超过出上限"),
    DB_NOT_EXIST("-15001", "数据源不存在"),
    ENTERPRISE_INFO_NOT_EXIST("-15002", "数据源对应的企业信息不存在"),
    SEARCH_TYPE_NOT_EXIST("-15003", "不支持该搜索类型"),
    DATA_NOT_EXIST("-15004", "该数据不存在"),
    SEARCH_COUNT_IS_OVER("-15005", "免费查询次数已用尽,请登录"),
    SEARCH_DEPTH_IS_OVER("-15006", "已达到最大翻页数,请进行数据下载"),
    DASHBOARD_NOT_EXIST("-16001", "面板不存在"),
    TAG_WORD_NOT_EXIST("-16002", "您输入的非合规xx,请重新输入"),
    DATA_DOWNLOAD_ERROR("-17001", "数据下载错误"),
    SEND_DATA_EMAIL_ERROR("-17002", "发送数据到邮箱失败"),
    FILE_NOT_EXIST("-17003", "下载文件不存在"),
    OVER_DOWNLOAD_COUNT("-17004", "文件下载次数已用尽"),
    TICKER_TYPE_NOT_EXISTS("-18001", "工单类型不存在"),
    HAS_CRITICISM("-19001", "点赞失败,此数据已被点踩"),
    PRAISE_RECORD_NOT_EXISTS("-19002", "取消点赞失败,点赞记录不存在"),
    HAS_PRAISE("-20001", "点踩失败,此数据已被点赞"),
    CRITICISM_RECORD_NOT_EXISTS("-20002", "取消点踩失败,点踩记录不存在"),
    RELATION_NO_REGION("-21002", "没有获取到详细省市县"),
    NEO4J_TIME_OUT("-21001", "neo4j服务器超时"),
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
