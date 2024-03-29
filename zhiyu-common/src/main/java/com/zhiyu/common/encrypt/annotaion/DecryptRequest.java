package com.zhiyu.common.encrypt.annotaion;

import java.lang.annotation.*;

@Target({ElementType.METHOD , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DecryptRequest {
    /**
     * 是否对body进行解密
     */
    boolean value() default true;
}