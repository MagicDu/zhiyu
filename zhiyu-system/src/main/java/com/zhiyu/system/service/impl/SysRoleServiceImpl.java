package com.zhiyu.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.common.constant.UserConstants;
import com.zhiyu.common.core.entity.SysRole;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.SysRoleMenu;
import com.zhiyu.system.entity.query.SysRoleQuery;
import com.zhiyu.system.mapper.SysRoleMapper;
import com.zhiyu.system.mapper.SysRoleMenuMapper;
import com.zhiyu.system.mapper.SysUserRoleMapper;
import com.zhiyu.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<String> getByUserId(Long userId) {
        return sysRoleMapper.getByUserId(userId);
    }

    @Override
    public IPage<SysRole> pageList(SysRoleQuery query) {
        IPage<SysRole> page=new Page<>(query.getCurrent(),query.getSize());
        return sysRoleMapper.pageQuery(page,query);
    }

    @Override
    public ApiResult<Boolean> addRole(SysRole role) {
        // 验证角色是否存在
        if (UserConstants.NOT_UNIQUE.equals(checkRoleNameUnique(role))) {
            return new ApiResult<>(false,-1,"新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(checkRoleKeyUnique(role))) {
            return new ApiResult<>(false,-1,"新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        // 保存角色的菜单权限
        save(role);
        List<SysRoleMenu> sysRoleMenus=new ArrayList<>();
        insertMenuRoles(role, sysRoleMenus);
        sysRoleMenuMapper.batchRoleMenu(sysRoleMenus);
        return new ApiResult<>(true);
    }

    @Override
    public String checkRoleNameUnique(SysRole role) {
        Long roleId = ObjectUtil.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = sysRoleMapper.checkRoleNameUnique(role.getRoleName());
        if (ObjectUtil.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkRoleKeyUnique(SysRole role) {
        Long roleId = ObjectUtil.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = sysRoleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (ObjectUtil.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public ApiResult<Boolean> updateRole(SysRole role) {
        if (UserConstants.NOT_UNIQUE.equals(checkRoleNameUnique(role))) {
            return new ApiResult<>(false,-1,"修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(checkRoleKeyUnique(role))) {
            return new ApiResult<>(false,-1,"修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        if (updateById(role)){
            // 删除角色与菜单关联
            sysRoleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
            List<SysRoleMenu> sysRoleMenus=new ArrayList<>();
            insertMenuRoles(role, sysRoleMenus);
            sysRoleMenuMapper.batchRoleMenu(sysRoleMenus);
        }
        return new ApiResult<>(true);
    }


    @Override
    public ApiResult<Boolean> removeRoles(List<Long> roleIds) {
        // 判断角色下是否有人
      for (Long roleId: roleIds){
          if(countUserRoleByRoleId(roleId)>0){
              SysRole role=getById(roleId);
              return new ApiResult<>(false,-1,"角色"+role.getRoleName()+"已经被分配，不能删除");
          }
      }
        // 删除角色绑定表
        sysRoleMenuMapper.deleteRoleMenu(roleIds);

        return new ApiResult<>(removeByIds(roleIds));
    }

    @Override
    public  int countUserRoleByRoleId(Long roleId) {
        return sysUserRoleMapper.countUserRoleByRoleId(roleId);
    }


    private void insertMenuRoles(SysRole role, List<SysRoleMenu> sysRoleMenus) {
        for (Long menuId:role.getMenuIds()){
            SysRoleMenu sysRoleMenu=new SysRoleMenu();
            sysRoleMenu.setRoleId(role.getRoleId());
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenus.add(sysRoleMenu);
        }
    }
}
