package com.zhiyu.system.entity.query;

import com.zhiyu.common.core.entity.BasePageInfo;
import com.zhiyu.common.core.entity.SysRole;
import lombok.Data;

@Data
public class SysRoleQuery extends BasePageInfo<SysRole> {
    private String roleName;
    private String roleKey;
}
