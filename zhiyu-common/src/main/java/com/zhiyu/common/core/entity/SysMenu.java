package com.zhiyu.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class SysMenu extends BaseEntity {
    @TableId
    private Long menuId;
    private String menuName;
    private Long parentId;
    private Long orderNum;
    private String path;
    private String component;
    private String layout;
    private String query;
    private String isFrame;
    private String isCache;
    private String menuType;
    private String visible;
    private String status;
    private String perms;
    private String icon;
    @TableField(exist = false)
    private List<SysMenu> children;
    @TableField(exist = false)
    private Long userId;
}
