package com.zhiyu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.system.entity.SysUserRole;
import com.zhiyu.system.mapper.SysUserRoleMapper;
import com.zhiyu.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper,SysUserRole> implements SysUserRoleService {
}
