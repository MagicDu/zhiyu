package com.zhiyu.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.SysDictData;
import com.zhiyu.system.entity.query.SysDictDataQuery;
import com.zhiyu.system.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典类型管理
 * @author magicdu
 * @since 2022/01/12
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController {



        @Autowired
        private SysDictDataService sysDictDataService;

        @PostMapping("/list")
        public ApiResult<IPage<SysDictData>> list(@RequestBody SysDictDataQuery query) {
            return new ApiResult<>(sysDictDataService.pageQuery(query));
        }


        @GetMapping(value = "/{dictCode}")
        public ApiResult<SysDictData> getInfo(@PathVariable Long dictCode) {
            return new ApiResult<>(sysDictDataService.getById(dictCode));
        }


        @PostMapping("/saveOrUpdate")
        public ApiResult<Boolean> saveOrUpdate(@RequestBody SysDictData sysDictData) {
            return new ApiResult<>(sysDictDataService.saveOrUpdate(sysDictData));
        }


        @DeleteMapping("/remove")
        public ApiResult<Boolean> remove(@RequestBody List<String> ids) {
            return new ApiResult<>(sysDictDataService.removeByIds(ids));
        }

        /**
         * 根据字典类型查询字典数据信息
         */
        @GetMapping(value = "/type/{dictType}")
        public ApiResult<List<SysDictData>> dictType(@PathVariable String dictType) {
            List<SysDictData> data = sysDictDataService.selectDictDataByType(dictType);
            return new ApiResult<>(data);
        }
}
