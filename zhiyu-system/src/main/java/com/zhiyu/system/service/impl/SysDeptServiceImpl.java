package com.zhiyu.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.common.core.entity.SysDept;
import com.zhiyu.common.core.entity.TreeSelect;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.query.SysDeptQuery;
import com.zhiyu.system.mapper.SysDeptMapper;
import com.zhiyu.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<SysDept> selectDeptList(SysDept dept) {
        return sysDeptMapper.selectDeptList(dept);
    }

    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts) {
        List<SysDept> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public List<SysDept> buildDeptTree(List<SysDept> depts) {
        List<SysDept> returnList = new ArrayList<SysDept>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysDept dept : depts)
        {
            tempList.add(dept.getDeptId());
        }
        for (Iterator<SysDept> iterator = depts.iterator(); iterator.hasNext();)
        {
            SysDept dept = (SysDept) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = depts;
        }
        return returnList;
    }


    /**
     * 递归列表
     */
    private void recursionFn(List<SysDept> list, SysDept t) {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDept tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
        List<SysDept> tlist = new ArrayList<SysDept>();
        Iterator<SysDept> it = list.iterator();
        while (it.hasNext())
        {
            SysDept n = (SysDept) it.next();
            if (ObjectUtil.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getDeptId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t) {
        return getChildList(list, t).size() > 0;
    }
}
