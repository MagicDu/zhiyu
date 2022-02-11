package com.zhiyu.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.common.constant.UserConstants;
import com.zhiyu.system.entity.SysDictType;
import com.zhiyu.system.entity.query.SysDictTypeQuery;
import com.zhiyu.system.mapper.SysDictTypeMapper;
import com.zhiyu.system.service.SysDictTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper,SysDictType> implements SysDictTypeService {

    @Override
    public IPage<SysDictType> pageQuery(SysDictTypeQuery query) {
        IPage<SysDictType> page=new Page<>(query.getCurrent(),query.getSize());
        return baseMapper.pageQuery(page,query);
    }

    @Override
    public String checkDictTypeUnique(SysDictType  dict) {
        Long dictId = ObjectUtil.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = baseMapper.checkDictTypeUnique(dict.getDictType());
        if (ObjectUtil.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public List<SysDictType> selectDictTypeAll() {
        return baseMapper.selectDictTypeAll();
    }
}

