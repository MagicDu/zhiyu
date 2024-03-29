package com.zhiyu.common.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 统一响应信息主体
 */
@Data
public class ApiResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
    private T data;
    private Integer code = ResponseCodeEnum.SUCCESS.getCode();
    private String message = ResponseCodeEnum.SUCCESS.getMessage();
    //private String key;
    public ApiResult() {
    	
    }
    public ApiResult(T data) {
        this.data = data;
    }
    public ApiResult(T data, String message) {
        this.data = data;
        this.message = message;
    }
    public ApiResult(T data, ResponseCodeEnum responseCode) {
        this.data = data;
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
    public ApiResult(Throwable throwable) {
        this.message = throwable.getMessage();
        this.code = ResponseCodeEnum.FAIL.getCode();
    }
    public ApiResult(Integer code, String message) {
      this.code = code;
      this.message = message;
  }
    public ApiResult(Throwable throwable, ResponseCodeEnum  code) {
        this.message = throwable.getMessage();
        this.code = code.getCode();
    }
	 public ApiResult(T data, Integer code , String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
//    public ApiResult(T data, Integer code , String message,String key) {
//        this.data = data;
//        this.code = code;
//        this.message = message;
//        this.key=key;
//    }
}