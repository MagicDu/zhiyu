package com.zhiyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.system.entity.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {
    List<String> getByUserId(Long userId);
}
