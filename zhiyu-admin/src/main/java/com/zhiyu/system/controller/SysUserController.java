package com.zhiyu.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.core.entity.SysRole;
import com.zhiyu.common.core.entity.SysUser;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.framework.security.SecurityUtils;
import com.zhiyu.system.entity.query.SysUserQuery;
import com.zhiyu.system.service.SysRoleService;
import com.zhiyu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/user")
public class SysUserController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;

    @PostMapping("/addUser")
    public ApiResult<Boolean> addUser(@RequestBody SysUser user){
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return userService.addUser(user);
    }

    @PostMapping("/updateUser")
    public ApiResult<Boolean> updateUser(@RequestBody SysUser user){
        return userService.updateUser(user);
    }

    @PostMapping("/list")
    public ApiResult<IPage<SysUser>> list(@RequestBody SysUserQuery query){
        return  new ApiResult<>(userService.pageQuery(query));
    }

    @GetMapping(value = { "/", "/{userId}" })
    public ApiResult<Map<String,Object>> getInfo(@PathVariable(value = "userId", required = false) Long userId){
        Map<String,Object> result=new HashMap<>();
        List<SysRole> roles = roleService.list();
        result.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        result.put("posts",new ArrayList<>());
        if(ObjectUtil.isNotNull(userId)){
            SysUser sysUser = userService.selectUserById(userId);
            result.put("data", sysUser);
            result.put("postIds", new ArrayList<>());
            result.put("roleIds", sysUser.getRoles().stream().map(SysRole::getRoleId).collect(Collectors.toList()));
        }
        return new ApiResult<>(result);
    }
}
