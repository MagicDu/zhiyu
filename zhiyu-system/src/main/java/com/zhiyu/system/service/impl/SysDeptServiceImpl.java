package com.zhiyu.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.common.core.entity.SysDept;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.query.SysDeptQuery;
import com.zhiyu.system.mapper.SysDeptMapper;
import com.zhiyu.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper,SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Override
    public IPage<SysDept> pageQuery(SysDeptQuery sysDeptQuery) {
        IPage<SysDept> page=new Page<>(sysDeptQuery.getCurrent(),sysDeptQuery.getSize());
        return sysDeptMapper.pageQuery(page,sysDeptQuery);
    }

    @Override
    public Boolean saveOrUpdateDept(SysDept sysDept) {
        SysDept info = getById(sysDept.getParentId());
        sysDept.setAncestors(info.getAncestors() + "," + sysDept.getParentId());
        return saveOrUpdate(sysDept);
    }

    @Override
    public ApiResult<Boolean> removeDepts(List<Long> deptIds) {
        for(Long deptId:deptIds){
            // 判断是否存在下级部门
            if(hasChildByDeptId(deptId)){
                SysDept dept=getById(deptId);
                return new ApiResult<>(false,-1,dept.getDeptName()+"下存在子机构，不允许删除");
            }
            if(hasPeopleByDeptId(deptId)){
                SysDept dept=getById(deptId);
                return new ApiResult<>(false,-1,dept.getDeptName()+"下存在人员信息，不允许删除");
            }
            // 判断机构下是否有人员
        }
        return new ApiResult<>(removeByIds(deptIds));
    }

    @Override
    public Boolean hasChildByDeptId(Long deptId) {
        int result = sysDeptMapper.hasChildByDeptId(deptId);
        return result > 0;
    }

    @Override
    public Boolean hasPeopleByDeptId(Long deptId) {
        int result = sysDeptMapper.checkDeptExistUser(deptId);
        return result > 0;
    }
}
