package com.zhiyu.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.common.core.entity.SysPost;
import com.zhiyu.system.entity.query.SysPostQuery;
import com.zhiyu.system.mapper.SysPostMapper;
import com.zhiyu.system.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

    @Autowired
    private SysPostMapper sysPostMapper;
    @Override
    public IPage<SysPost> pageQuery(SysPostQuery query) {
        IPage<SysPost> page = new Page<>(query.getCurrent(),query.getSize());
        return sysPostMapper.pageQuery(page,query);
    }
}
