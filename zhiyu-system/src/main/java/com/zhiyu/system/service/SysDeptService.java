package com.zhiyu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.common.core.entity.SysDept;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.query.SysDeptQuery;

import java.util.List;

public interface SysDeptService extends IService<SysDept> {
    IPage<SysDept> pageQuery(SysDeptQuery sysDeptQuery);

    Boolean saveOrUpdateDept(SysDept sysDept);

    ApiResult<Boolean> removeDepts(List<Long> deptIds);

    Boolean hasChildByDeptId(Long deptId);

    Boolean hasPeopleByDeptId(Long deptId);

}
