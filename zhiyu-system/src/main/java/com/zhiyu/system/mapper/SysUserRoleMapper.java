package com.zhiyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyu.system.entity.SysUserRole;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    int countUserRoleByRoleId(Long roleId);
}
