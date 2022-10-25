package com.wang.blogsystem.common;

public enum ResultCode {
    SUCCESS(200,"success"),
    ERROR(500,"internal server error"),
    //登录
    LOGIN_ERROR(50001,"用户名或密码错误"),
    CODE_ERROR(50002,"验证码为空或已过期"),
    CODE_ERROR_EMPTY(50002,"验证码为空或已过期"),
    USER_NOT(50003,"用户不存在"),
    REGISTER_ERROR(50004,"注册失败"),
    LOGIN_ERROR_EMPTY(50005,"用户名或密码为空"),



    //20001 ---- 参数异常
    PARAM_TYPE_INVALID(20001,"param type invalid"),
    PARAM_NOT_EXIST(20002,"param not exist"),

    //30001 --- 用户认证异常
    USER_FORRBIN(30001,"用户没有权限"),
    USER_NOT_LOGIN(30002,"用户未认证！"),
    AUTH_FAILURE(30003,"认证失败！"),

    //40001 ----- 数据异常
    DATA_NOT_EXIST(40001,"数据不存在！"),
    DATA_IS_EMPTY(40002,"数据为空！"),
    DATA_EXIST(40003,"数据已经存在！");
    private int code;
    private String msg;
    private ResultCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
    public int getCode() {
        return code;
    }
}

