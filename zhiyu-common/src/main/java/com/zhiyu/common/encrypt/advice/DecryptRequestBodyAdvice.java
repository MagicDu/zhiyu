package com.zhiyu.common.encrypt.advice;


import com.zhiyu.common.encrypt.config.KeyConfig;
import com.zhiyu.common.encrypt.utils.NeedCrypto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * decrypt request data  advice
 */
@ControllerAdvice
@ConditionalOnProperty(prefix = "spring.crypto.request.decrypt", name = "enabled" , havingValue = "true", matchIfMissing = true)
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {
    @Autowired
    private KeyConfig keyConfig;

    /**
     * needed decrypt or not
     */
    private boolean isDecode;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        isDecode= NeedCrypto.needDecrypt(methodParameter);
        return isDecode;
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
        return obj;
    }

    @Override
    public Object handleEmptyBody(Object obj, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return obj;
    }
}
