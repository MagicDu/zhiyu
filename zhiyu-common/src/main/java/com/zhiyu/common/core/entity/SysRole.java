package com.zhiyu.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysRole extends BaseEntity {
    @TableId
    private Long roleId;
    private String roleName;
    private String roleKey;
    private String roleSort;
    private String dataScope;
    private boolean menuCheckStrictly;
    private boolean deptCheckStrictly;
     /** 菜单组 */
     @TableField(exist = false)
    private Long[] menuIds;
    /** 部门组（数据权限） */
    @TableField(exist = false)
    private Long[] deptIds;

}
