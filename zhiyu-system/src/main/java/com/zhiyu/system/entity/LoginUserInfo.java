package com.zhiyu.system.entity;

import com.zhiyu.common.core.entity.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class LoginUserInfo {
    private SysUser user;
    private List<String> roles;
    private List<String> permissions;
}
