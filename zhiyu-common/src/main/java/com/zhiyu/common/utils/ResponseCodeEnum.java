package com.zhiyu.common.utils;

/**
 * 响应信息枚举
 * @author magicdu
 * @since  2021/12/20
 */
public enum ResponseCodeEnum {

    /**
	 * 操作成功   0开头
	 */
    SUCCESS(0, "操作成功"),

    /**
	 * 操作失败 -100_开头
	 */
    FAIL(-1,"操作失败"),

    PERMISSION_DEFINED(401,"permission defined"),
    
    /**
	 * 没有登录 -200_开头
	 */
    NOT_LOGIN(-2000,"请先登录");




    private Integer code;

    private String message;

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
