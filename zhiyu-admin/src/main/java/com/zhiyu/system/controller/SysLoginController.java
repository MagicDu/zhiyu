package com.zhiyu.system.controller;

import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.framework.security.SecurityUtils;
import com.zhiyu.framework.service.LoginService;
import com.zhiyu.system.entity.LoginInfo;
import com.zhiyu.system.entity.LoginUserInfo;
import com.zhiyu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SysLoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody LoginInfo user){
        return loginService.login(user);
    }

    @GetMapping("/userinfo")
    public ApiResult<LoginUserInfo> getUserInfo(){
        Long userId=SecurityUtils.getUserId();
        return loginService.getUserInfo(userId);
    }
}
