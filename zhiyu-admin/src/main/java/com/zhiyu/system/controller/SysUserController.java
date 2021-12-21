package com.zhiyu.system.controller;

import com.zhiyu.common.core.entity.SysUser;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.framework.security.SecurityUtils;
import com.zhiyu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class SysUserController {
    @Autowired
    private SysUserService userService;

    @PostMapping("/addUser")
    public ApiResult<Boolean> addUser(@RequestBody SysUser user){
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return userService.addUser(user);
    }
}
