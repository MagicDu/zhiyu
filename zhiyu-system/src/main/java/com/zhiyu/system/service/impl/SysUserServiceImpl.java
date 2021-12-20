package com.zhiyu.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.LoginUser;
import com.zhiyu.system.entity.SysUser;
import com.zhiyu.system.entity.SysUserRole;
import com.zhiyu.system.mapper.SysUserMapper;
import com.zhiyu.system.service.SysUserRoleService;
import com.zhiyu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService ,UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user=findUserByUserName(username);
        return new LoginUser(user.getUserId(),user.getDeptId(),user,null);
    }

    @Override
    public SysUser findUserByUserName(String username) {
        return sysUserMapper.findUserByUserName(username);
    }

    @Override
    public ApiResult<Boolean> addUser(SysUser user) {
        // 校验用户名是否重复
        String username=user.getUserName();
        user.setStatus("0");
        if(checkUniqueUserName(username)){
            return new ApiResult<>(-1,"用户名已被占用");
        }
        save(user);
        SysUserRole userRole=new SysUserRole();
        userRole.setUserId(user.getUserId());
        List<SysUserRole> sysUserRoleList=new ArrayList<>();
        user.getRoleIds().forEach(role->{
            userRole.setRoleId(role);
            sysUserRoleList.add(userRole);
        });
        sysUserRoleService.saveBatch(sysUserRoleList);
        return new ApiResult<>(true);
    }

    private boolean checkUniqueUserName(String username) {
        return ObjectUtil.isNotNull(findUserByUserName(username));
    }
}
