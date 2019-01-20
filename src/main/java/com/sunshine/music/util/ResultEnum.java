package com.sunshine.music.util;

public enum ResultEnum {

    UNKOWN_ERROR("-1","未知错误"),
    BUSINESS_ERROR("-2","业务错误"),
    SUCCESS("0","成功");

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
