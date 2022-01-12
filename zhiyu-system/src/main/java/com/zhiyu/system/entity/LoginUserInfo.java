package com.zhiyu.system.entity;

import com.zhiyu.common.core.entity.SysUser;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class LoginUserInfo {
    private SysUser user;
    private List<String> roles;
    private Set<String> permissions;
}
