package com.zhiyu.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.core.entity.SysPost;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.query.SysPostQuery;
import com.zhiyu.system.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * post controller
 * @author magicdu
 * @date 2023/04/18
 */
@RestController
@RequestMapping("/system/post")
public class SysPostController {

    @Autowired
    private SysPostService sysPostService;
    /**
     * add post
     * @param sysPost
     * @return
     */
    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody SysPost sysPost) {
        return new ApiResult<>(sysPostService.save(sysPost));
    }
    /**
     * 分页查询
     * @param sysPost
     * @return
     */
    @PostMapping("/list")
    public ApiResult<IPage<SysPost>> list(@RequestBody SysPostQuery query) {
        return new ApiResult<>(sysPostService.pageQuery(query));
    }

    @DeleteMapping("/remove")
    public ApiResult<Boolean> remove(@RequestBody List<Long> ids) {
        return new ApiResult<>(sysPostService.removeByIds(ids));
    }

    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody SysPost sysPost) {
        return new ApiResult<>(sysPostService.updateById(sysPost));
    }

    @GetMapping("getById/{id}")
    public ApiResult<SysPost> getById(@PathVariable("id") Long id) {
        return new ApiResult<>(sysPostService.getById(id));
    }

}
