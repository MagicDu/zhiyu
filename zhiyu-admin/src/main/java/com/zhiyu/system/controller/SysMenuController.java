package com.zhiyu.system.controller;

import com.zhiyu.common.core.entity.SysMenu;
import com.zhiyu.common.core.entity.TreeSelect;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.framework.security.SecurityUtils;
import com.zhiyu.system.entity.query.SysMenuQuery;
import com.zhiyu.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/system/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService menuService;

    @PostMapping("/addMenu")
    public ApiResult<Boolean> addMenu(@RequestBody SysMenu menu){
        return  menuService.addMenu(menu);
    }


    @PostMapping("/treeselect")
    public ApiResult<List<TreeSelect>> treeSelect(){
        List<SysMenu> menuList= menuService.selectMenuList(new SysMenu(),SecurityUtils.getUserId());
        return new ApiResult<>(menuService.buildMenuTreeSelect(menuList));
    }

    @PostMapping("/list")
    public ApiResult<List<SysMenu>> list(@RequestBody SysMenu query){
        List<SysMenu> menus = menuService.selectMenuList(query, SecurityUtils.getUserId());
        return new ApiResult<>(menus);
    }

    @PostMapping("/saveOrUpdate")
    public ApiResult<Boolean> saveOrUpdate(@RequestBody SysMenu menu) {
        return new ApiResult<>(menuService.saveOrUpdate(menu));
    }

    @GetMapping("/{id}")
    public ApiResult<SysMenu> getMenu(@PathVariable(name = "id") Long id) {
        return new ApiResult<>(menuService.getById(id));
    }


    @DeleteMapping ("/{id}")
    public ApiResult<Boolean> deleteMenu(@PathVariable(name = "id") Long id) {
        if(menuService.hasChildByMenuId(id)){
            return new ApiResult<>(false,-1,"存在子菜单，不允许删除");
        }
        if(menuService.checkMenuExistRole(id)){
            return new ApiResult<>(false,-1,"菜单已经分配，不允许删除");
        }
        return new ApiResult<>(menuService.removeById(id));
    }

    }
