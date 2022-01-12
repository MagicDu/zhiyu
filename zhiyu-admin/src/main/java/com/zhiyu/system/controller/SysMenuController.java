package com.zhiyu.system.controller;

import com.zhiyu.common.core.entity.SysMenu;
import com.zhiyu.common.core.entity.TreeSelect;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.framework.security.SecurityUtils;
import com.zhiyu.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/system/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService menuService;

    @PostMapping("addMenu")
    public ApiResult<Boolean> addMenu(@RequestBody SysMenu menu){
        return  menuService.addMenu(menu);
    }


    @PostMapping("treeselect")
    public ApiResult<List<TreeSelect>> treeSelect(){
        List<SysMenu> menuList= menuService.selectMenuList(new SysMenu(),SecurityUtils.getUserId());
        return new ApiResult<>(menuService.buildMenuTreeSelect(menuList));
    }




}
