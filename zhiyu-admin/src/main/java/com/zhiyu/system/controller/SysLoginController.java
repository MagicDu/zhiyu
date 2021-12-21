package com.zhiyu.system.controller;

import com.zhiyu.common.core.entity.SysMenu;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.framework.security.SecurityUtils;
import com.zhiyu.framework.service.LoginService;
import com.zhiyu.system.entity.LoginInfo;
import com.zhiyu.system.entity.LoginUserInfo;
import com.zhiyu.system.entity.vo.RouterVo;
import com.zhiyu.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class SysLoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SysMenuService menuService;

    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody LoginInfo user) {
        return loginService.login(user);
    }

    @GetMapping("/userinfo")
    public ApiResult<LoginUserInfo> getUserInfo() {
        Long userId = SecurityUtils.getUserId();
        return loginService.getUserInfo(userId);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public ApiResult<List<RouterVo>> getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return new ApiResult<>(menuService.buildMenus(menus));
    }
}
