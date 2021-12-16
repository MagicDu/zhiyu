package com.zhiyu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.system.entity.LoginUser;
import com.zhiyu.system.entity.SysUser;
import com.zhiyu.system.mapper.SysUserMapper;
import com.zhiyu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService ,UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user=findUserByUserName(username);
        return new LoginUser(user.getUserId(),user.getDeptId(),user,null);
    }

    @Override
    public SysUser findUserByUserName(String username) {
        return sysUserMapper.findUserByUserName(username);
    }
}
