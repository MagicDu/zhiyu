package com.zhiyu.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.system.entity.SysDictData;
import com.zhiyu.system.entity.query.SysDictDataQuery;
import com.zhiyu.system.mapper.SysDictDataMapper;
import com.zhiyu.system.service.SysDictDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper,SysDictData> implements SysDictDataService {
    @Override
    public IPage<SysDictData> pageQuery(SysDictDataQuery query) {
        IPage<SysDictData> page=new Page<>(query.getCurrent(),query.getSize());
        return baseMapper.pageQuery(page,query);
    }

    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return baseMapper.selectDictDataByType(dictType);
    }
}
