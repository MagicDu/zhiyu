package com.zhiyu.framework.security;

import cn.hutool.log.Log;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理器
 * @author magicdu
 * @since 2021/11/23
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint,Serializable {
    private static final Log log = Log.get();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        String requestUri = request.getRequestURI();
        log.info("当前请求的地址是------------------>："+requestUri);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException==null?"Unauthorized":authException.getMessage());
    }
}
