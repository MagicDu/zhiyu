package com.zhiyu.framework.service.impl;

import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.framework.security.TokenService;
import com.zhiyu.framework.service.LoginService;
import com.zhiyu.system.entity.LoginInfo;
import com.zhiyu.system.entity.LoginUser;
import com.zhiyu.system.entity.LoginUserInfo;
import com.zhiyu.system.service.SysMenuService;
import com.zhiyu.system.service.SysRoleService;
import com.zhiyu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;


    @Resource
    private AuthenticationManager authenticationManager;
    @Override
    public ApiResult<String> login(LoginInfo user) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        }catch (Exception e){
           return new ApiResult<>(-1,"用户名或者密码错误");
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 记录用户登录信息
        return new ApiResult<>(tokenService.createToken(loginUser));
    }


    @Override
    public ApiResult<LoginUserInfo> getUserInfo(Long userId) {
        LoginUserInfo userInfo=new LoginUserInfo();
        userInfo.setUser(userService.getById(userId));
        userInfo.setRoles(sysRoleService.getByUserId(userId));
        userInfo.setPermissions(sysMenuService.getPermsByUserId(userId));
        return new ApiResult<>(userInfo);
    }

}
