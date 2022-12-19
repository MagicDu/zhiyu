package com.zhiyu.common.filter;

import com.zhiyu.common.annotaion.DecryptRequest;
import com.zhiyu.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

@ControllerAdvice
@ConditionalOnProperty(prefix = "spring.crypto.request.decrypt", name = "enabled" , havingValue = "true", matchIfMissing = true)
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {
    @Autowired
    private KeyConfig keyConfig;

    /**
     * 是否需要解码
     */
    private boolean isDecode;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        // 方法或类上有注解
        if (Utils.hasMethodAnnotation(methodParameter,new Class[]{DecryptRequest.class})) {
            isDecode=true;
            // 这里返回true 才支持
            return true;
        }
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        if(isDecode){
            return new DecodeInputMessage(httpInputMessage, keyConfig);
        }
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object obj, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        // 这里就是已经读取到body了，obj就是
        return obj;
    }

    @Override
    public Object handleEmptyBody(Object obj, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        // body 为空的时候调用
        return obj;
    }
}
