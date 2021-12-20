package com.zhiyu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.system.entity.SysRole;
import com.zhiyu.system.mapper.SysRoleMapper;
import com.zhiyu.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public List<String> getByUserId(Long userId) {
        return sysRoleMapper.getByUserId(userId);
    }
}
