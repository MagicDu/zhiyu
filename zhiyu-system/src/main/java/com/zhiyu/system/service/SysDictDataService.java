package com.zhiyu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.system.entity.SysDictData;
import com.zhiyu.system.entity.query.SysDictDataQuery;

import java.util.List;

public interface SysDictDataService extends IService<SysDictData> {
    IPage<SysDictData> pageQuery(SysDictDataQuery query);
    List<SysDictData> selectDictDataByType(String dictType);
}
