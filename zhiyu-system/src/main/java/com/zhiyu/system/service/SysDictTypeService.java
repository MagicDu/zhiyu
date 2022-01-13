package com.zhiyu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.system.entity.SysDictType;
import com.zhiyu.system.entity.query.SysDictTypeQuery;

public interface SysDictTypeService extends IService<SysDictType> {
    IPage<SysDictType> pageQuery(SysDictTypeQuery query);
    String checkDictTypeUnique(SysDictType sysDictType);
}
