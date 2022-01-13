package com.zhiyu.framework.security;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;

import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.framework.utils.ServletUtils;
import com.zhiyu.system.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (ObjectUtil.isNotNull(loginUser)) {
            tokenService.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(response, JSON.toJSONString(new ApiResult<>(true,0,"退出成功")));
    }
}
