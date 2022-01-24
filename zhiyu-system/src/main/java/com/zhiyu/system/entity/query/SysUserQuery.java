package com.zhiyu.system.entity.query;

import com.zhiyu.common.core.entity.BasePageInfo;
import com.zhiyu.common.core.entity.SysUser;
import lombok.Data;

@Data
public class SysUserQuery extends BasePageInfo<SysUser> {
    private String userName;
    private String nickName;
    private Long deptId;
    private String phonenumber;
    private String sex;
}
