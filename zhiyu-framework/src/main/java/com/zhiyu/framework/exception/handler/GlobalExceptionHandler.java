package com.zhiyu.framework.exception.handler;


import com.zhiyu.framework.exception.ServiceException;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.common.utils.ThrowableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

/**
 * 全局异常处理
 * @author  magicdu
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log=LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return new ApiResult(BAD_REQUEST.value(),e.getMessage());
    }

    /**
     * 处理 接口无权访问异常AccessDeniedException
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResult handleAccessDeniedException(AccessDeniedException e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return new ApiResult(FORBIDDEN.value(),e.getMessage());
    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
	@ExceptionHandler(value = ServiceException.class)
	public ApiResult badRequestException(ServiceException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return new ApiResult(e.getCode(),e.getMessage());
	}



    /**
     * 处理所有接口数据验证异常
     * @param e
     * @returns
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        String[] str = e.getBindingResult().getAllErrors().get(0).getCodes()[1].split("\\.");
        StringBuffer msg = new StringBuffer(str[1]+":");
        msg.append(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ApiResult(BAD_REQUEST.value(),msg.toString());
    }
}
