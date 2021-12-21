package com.zhiyu.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class SysDept extends BaseEntity {
    @TableId
    private Long deptId;
    private Long parentId;
    private String ancestors;
    private String deptName;
    private String deptType;
    private String orderNum;
    private String leader;
    private String phone;
    private String email;
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private String streetCode;
    private String deptCode;
    @TableField(exist = false)
    private List<SysDept> children;
}

