package com.zhiyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.system.entity.SysDictData;
import com.zhiyu.system.entity.query.SysDictDataQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictDataMapper extends BaseMapper<SysDictData> {
    IPage<SysDictData> pageQuery(IPage<SysDictData> page,@Param("query") SysDictDataQuery query);

    List<SysDictData> selectDictDataByType(String dictType);
}
