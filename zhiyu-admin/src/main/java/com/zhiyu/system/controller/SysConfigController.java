package com.zhiyu.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.constant.UserConstants;
import com.zhiyu.common.core.entity.SysDept;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.SysConfig;
import com.zhiyu.system.entity.query.SysConfigQuery;
import com.zhiyu.system.entity.query.SysDeptQuery;
import com.zhiyu.system.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;


    @PostMapping("/list")
    public ApiResult<IPage<SysConfig>> list(@RequestBody SysConfigQuery sysConfigQuery){
        return new ApiResult<>(sysConfigService.pageQuery(sysConfigQuery));
    }

    @GetMapping(value = "/{configId}")
    public ApiResult<SysConfig> getInfo(@PathVariable Long configId) {
        return new ApiResult<>(sysConfigService.getById(configId));
    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public ApiResult<String> getConfigKey(@PathVariable String configKey) {
        return new ApiResult<>(sysConfigService.selectConfigByKey(configKey));
    }

    @PostMapping("addConfig")
    public ApiResult<Boolean> addConfig(@RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(sysConfigService.checkConfigKeyUnique(config))) {
            return new ApiResult<>(false,-1,"新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return new ApiResult<>(sysConfigService.save(config));
    }


    @PostMapping("editConfig")
    public ApiResult<Boolean> edit(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(sysConfigService.checkConfigKeyUnique(config))) {
            return new ApiResult<>(false,-1,"修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return new ApiResult<>(sysConfigService.updateById(config));
    }

    @DeleteMapping("deleteByIds")
    public ApiResult<Boolean> remove(@RequestBody List<Long> configIds) {
        return new ApiResult<>(sysConfigService.removeByIds(configIds));
    }

    @PostMapping("/refreshCache")
    public ApiResult<Boolean> refreshCache() {
        sysConfigService.resetConfigCache();
        return new ApiResult<>(true);
    }

}
