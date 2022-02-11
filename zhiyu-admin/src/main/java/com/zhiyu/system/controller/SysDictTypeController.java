package com.zhiyu.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.constant.UserConstants;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.SysDictData;
import com.zhiyu.system.entity.SysDictType;
import com.zhiyu.system.entity.query.SysDictTypeQuery;
import com.zhiyu.system.service.SysDictDataService;
import com.zhiyu.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典管理
 *
 * @author magicdu
 * @since 2022/01/12
 */
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController {

    @Autowired
    private SysDictTypeService sysDictTypeService;
    @Autowired
    private SysDictDataService sysDictDataService;

    @PostMapping("/list")
    public ApiResult<IPage<SysDictType>> list(@RequestBody SysDictTypeQuery query) {
        return new ApiResult<>(sysDictTypeService.pageQuery(query));
    }


    @GetMapping(value = "/{dictId}")
    public ApiResult<SysDictType> getInfo(@PathVariable Long dictId) {
        return new ApiResult<>(sysDictTypeService.getById(dictId));
    }


    @PostMapping("/saveOrUpdate")
    public ApiResult<Boolean> saveOrUpdate(@RequestBody SysDictType sysDictType) {
        if (ObjectUtil.isNotNull(sysDictType)) {
            // add
            if (UserConstants.NOT_UNIQUE.equals(sysDictTypeService.checkDictTypeUnique(sysDictType))) {
                return new ApiResult<>(false, -1, "字典值已经存在");
            }
        }
        return new ApiResult<>(sysDictTypeService.saveOrUpdate(sysDictType));
    }


    @DeleteMapping("/remove")
    public ApiResult<Boolean> remove(@RequestBody List<String> ids) {
        for (String id : ids) {// count  data
            QueryWrapper<SysDictData> queryWrapper = new QueryWrapper<>();
            String type = sysDictTypeService.getById(id).getDictType();
            queryWrapper.eq("dict_type", type);
            int count = sysDictDataService.count(queryWrapper);
            if (count > 0) {
                return new ApiResult<>(false, -1, "当前字典类型" + type + "已经存在数据，无法删除");
            }
        }
        return new ApiResult<>(sysDictTypeService.removeByIds(ids));
    }


    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public ApiResult<List<SysDictType>> optionselect() {
        List<SysDictType> dictTypes = sysDictTypeService.selectDictTypeAll();
        return new ApiResult<>(dictTypes);
    }


}
