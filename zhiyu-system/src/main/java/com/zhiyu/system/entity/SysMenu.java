package com.zhiyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.zhiyu.common.core.entity.BaseEntity;
import lombok.Data;

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
}
