package com.zhiyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.system.entity.SysMenu;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {
    List<String> getPermsByUserId(Long userId);
}
