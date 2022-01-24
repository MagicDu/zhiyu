package com.zhiyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.system.entity.SysUserRole;
import org.springframework.stereotype.Service;

@Service
public interface SysUserRoleService extends IService<SysUserRole> {
    boolean deleteUserRoleByUserId(Long userId);
}
