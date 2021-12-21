package com.zhiyu.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class SysUser  extends BaseEntity {
    @TableId
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
    @TableField(exist = false)
    private List<Long> roleIds;
    @TableField(exist = false)
    private List<SysRole> roles;
}
