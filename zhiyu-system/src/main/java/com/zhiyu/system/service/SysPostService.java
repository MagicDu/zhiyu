package com.zhiyu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.common.core.entity.SysPost;
import com.zhiyu.system.entity.query.SysPostQuery;

public interface SysPostService extends IService<SysPost> {

    IPage<SysPost> pageQuery(SysPostQuery query);
}
