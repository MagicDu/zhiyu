package com.zhiyu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.common.core.entity.SysRole;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.query.SysRoleQuery;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {
    List<String> getByUserId(Long userId);

    IPage<SysRole> pageList(SysRoleQuery query);

    ApiResult<Boolean> addRole(SysRole role);

    String checkRoleNameUnique(SysRole role);

    String checkRoleKeyUnique(SysRole role);

    ApiResult<Boolean> updateRole(SysRole role);

    ApiResult<Boolean> removeRoles(List<Long> roleIds);

    int  countUserRoleByRoleId(Long roleId);
}
