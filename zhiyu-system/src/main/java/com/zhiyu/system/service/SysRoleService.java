package com.zhiyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.common.core.entity.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {
    List<String> getByUserId(Long userId);
}
