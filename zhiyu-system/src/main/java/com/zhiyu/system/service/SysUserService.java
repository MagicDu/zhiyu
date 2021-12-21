package com.zhiyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.zhiyu.common.core.entity.SysUser;
import com.zhiyu.common.utils.ApiResult;

public interface SysUserService extends IService<SysUser> {
    SysUser findUserByUserName(String username);

    ApiResult<Boolean> addUser(SysUser user);
}
