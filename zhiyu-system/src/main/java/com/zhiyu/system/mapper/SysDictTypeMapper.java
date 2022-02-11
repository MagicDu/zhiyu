package com.zhiyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.system.entity.SysDictType;
import com.zhiyu.system.entity.query.SysDictTypeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
    IPage<SysDictType> pageQuery(IPage<SysDictType> page, @Param("query") SysDictTypeQuery query);

    SysDictType checkDictTypeUnique(String dictType);

    List<SysDictType> selectDictTypeAll();
}
