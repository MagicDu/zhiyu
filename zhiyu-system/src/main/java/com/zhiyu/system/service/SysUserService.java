package com.zhiyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.system.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    SysUser findUserByUserName(String username);
}
