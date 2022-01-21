package com.zhiyu.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.core.entity.SysDept;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.query.SysDeptQuery;
import com.zhiyu.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;


    @PostMapping("/list")
    public ApiResult<IPage<SysDept>> list(@RequestBody SysDeptQuery sysDeptQuery){
        return new ApiResult<>(sysDeptService.pageQuery(sysDeptQuery));
    }

    @PostMapping("/saveOrUpdate")
    public ApiResult<Boolean> saveOrUpdate(@RequestBody SysDept sysDept){
        return new ApiResult<>(sysDeptService.saveOrUpdateDept(sysDept));
    }

    @GetMapping("/{id}")
    public ApiResult<SysDept> getInfo(@PathVariable(value = "id") Long deptId){
        return new ApiResult<>(sysDeptService.getById(deptId));
    }

    @GetMapping("/list/exclude/{id}")
    public ApiResult<List<SysDept>> list(@PathVariable(value = "id") Long deptId){
        return new ApiResult<>(sysDeptService.list().stream().filter(dept->!deptId.equals(dept.getDeptId())).collect(Collectors.toList()));
    }

    @DeleteMapping("/remove")
    public ApiResult<Boolean> remove(@RequestBody List<Long> deptIds){
        return sysDeptService.removeDepts(deptIds);
    }


}
