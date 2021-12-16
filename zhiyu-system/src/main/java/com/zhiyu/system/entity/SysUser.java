package com.zhiyu.system.entity;

import com.zhiyu.common.core.entity.BaseEntity;
import lombok.Data;

@Data
public class SysUser  extends BaseEntity {
    private Long userId;
    private Long deptId;
    private String userName;
    private String nickName;
    private String userType;
    private String email;
    private String phonenumber;
    private String sex;
    private String avatar;
    private String password;
    private String status;
}
