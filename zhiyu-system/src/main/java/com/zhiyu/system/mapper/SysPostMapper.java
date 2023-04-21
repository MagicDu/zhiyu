package com.zhiyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.core.entity.SysPost;
import com.zhiyu.system.entity.query.SysPostQuery;
import org.apache.ibatis.annotations.Param;

public interface SysPostMapper extends BaseMapper<SysPost>  {
    IPage<SysPost> pageQuery(IPage<SysPost> page,@Param("query") SysPostQuery query);
}
