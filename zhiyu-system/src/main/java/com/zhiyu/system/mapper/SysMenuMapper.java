package com.zhiyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyu.common.core.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> getPermsByUserId(Long userId);

    List<SysMenu> selectMenuListByUserId(@Param("menu") SysMenu sysMenu);

    List<SysMenu> selectMenuList(@Param("menu") SysMenu sysMenu);


    List<SysMenu> selectMenuTreeByUserId(Long userId);

    List<SysMenu> selectAllMenuTree();

    int hasChildByMenuId(Long id);

    List<SysMenu> selectMenuListByRoleId(@Param("roleId") Long roleId,@Param("menuCheckStrictly") boolean menuCheckStrictly);
}
