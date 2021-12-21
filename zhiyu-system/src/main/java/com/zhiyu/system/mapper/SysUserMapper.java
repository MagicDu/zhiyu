package com.zhiyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyu.common.core.entity.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser findUserByUserName(String username);
}
