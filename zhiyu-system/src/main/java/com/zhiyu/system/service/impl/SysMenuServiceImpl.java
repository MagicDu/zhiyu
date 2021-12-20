package com.zhiyu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.system.entity.SysMenu;
import com.zhiyu.system.mapper.SysMenuMapper;
import com.zhiyu.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper,SysMenu>  implements SysMenuService {
    @Autowired
    private SysMenuMapper menuMapper;
    @Override
    public List<String> getPermsByUserId(Long userId) {
        return menuMapper.getPermsByUserId(userId);
    }
}
