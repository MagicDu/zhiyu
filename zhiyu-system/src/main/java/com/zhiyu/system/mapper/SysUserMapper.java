package com.zhiyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.core.entity.SysUser;
import com.zhiyu.system.entity.query.SysUserQuery;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser findUserByUserName(String username);

    IPage<SysUser> pageQuery(IPage<SysUser> page, @Param("query") SysUserQuery query);

    SysUser selectUserById(Long userId);
}
