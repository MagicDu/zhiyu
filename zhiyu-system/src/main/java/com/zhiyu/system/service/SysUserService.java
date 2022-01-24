package com.zhiyu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.zhiyu.common.core.entity.SysUser;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.query.SysUserQuery;

public interface SysUserService extends IService<SysUser> {
    SysUser findUserByUserName(String username);

    ApiResult<Boolean> addUser(SysUser user);

    IPage<SysUser> pageQuery(SysUserQuery query);

    ApiResult<Boolean> updateUser(SysUser user);

    SysUser selectUserById(Long userId);
}
