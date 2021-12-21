package com.zhiyu.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysRole extends BaseEntity {
    @TableId
    private Integer roleId;
    private String roleName;
    private String roleKey;
    private String roleSort;
    private String dataScope;
    private Integer menuCheckStrictly;
    private Integer deptCheckStrictly;
}