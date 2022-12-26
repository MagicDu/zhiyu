package com.zhiyu.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.core.entity.SysRole;
import com.zhiyu.common.encrypt.annotaion.DecryptRequest;
import com.zhiyu.common.encrypt.annotaion.EncryptResponse;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.query.SysRoleQuery;
import com.zhiyu.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @PostMapping("/list")
    @EncryptResponse
    @DecryptRequest
    public ApiResult<IPage<SysRole>> list(@RequestBody SysRoleQuery query){
        return new ApiResult<>(sysRoleService.pageList(query));
    }


    @GetMapping("/{id}")
    public ApiResult<SysRole> getInfo(@PathVariable Long id){
        return new ApiResult<>(sysRoleService.getById(id));
    }


    @PostMapping("/addRole")
    public ApiResult<Boolean> addRole(@RequestBody SysRole role){
        return  sysRoleService.addRole(role);
    }

    @PostMapping("/updateRole")
    public ApiResult<Boolean> updateRole(@RequestBody SysRole role){
        return  sysRoleService.updateRole(role);
    }


    @DeleteMapping("/remove")
    public   ApiResult<Boolean> remove(@RequestBody List<Long> roleIds ){
        return sysRoleService.removeRoles(roleIds);
    }
}
