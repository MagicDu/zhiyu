package com.zhiyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyu.system.entity.SysRole;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<String> getByUserId(Long userId);
}
