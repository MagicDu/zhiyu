package com.zhiyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.core.entity.SysDept;
import com.zhiyu.system.entity.query.SysDeptQuery;
import org.apache.ibatis.annotations.Param;

public interface SysDeptMapper extends BaseMapper<SysDept> {
    IPage<SysDept> pageQuery(IPage<SysDept> page,@Param("query") SysDeptQuery sysDeptQuery);

    int hasChildByDeptId(Long deptId);

    int checkDeptExistUser(Long deptId);
}
