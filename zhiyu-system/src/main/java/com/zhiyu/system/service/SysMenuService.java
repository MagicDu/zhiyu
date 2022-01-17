package com.zhiyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.common.core.entity.SysMenu;
import com.zhiyu.common.core.entity.TreeSelect;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.vo.RouterVo;

import java.util.List;
import java.util.Set;

public interface SysMenuService extends IService<SysMenu> {
    Set<String> getPermsByUserId(Long userId);

    ApiResult<Boolean> addMenu(SysMenu menu);

    List<SysMenu> selectMenuList(SysMenu sysMenu, Long userId);

    List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menuList);

    List<SysMenu> buildMenuTree(List<SysMenu> menus);

    List<SysMenu> selectMenuTreeByUserId(Long userId);

    List<SysMenu> getChildPerms(List<SysMenu> list, int parentId);

    List<RouterVo> buildMenus(List<SysMenu> menus);

    boolean hasChildByMenuId(Long id);

    boolean checkMenuExistRole(Long id);
}
