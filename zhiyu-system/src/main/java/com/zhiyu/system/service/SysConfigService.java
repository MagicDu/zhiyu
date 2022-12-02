package com.zhiyu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.system.entity.SysConfig;
import com.zhiyu.system.entity.query.SysConfigQuery;

public interface SysConfigService extends IService<SysConfig> {
    IPage<SysConfig> pageQuery(SysConfigQuery sysConfigQuery);

    String selectConfigByKey(String configKey);

    String checkConfigKeyUnique(SysConfig config);

    void resetConfigCache();

    void clearConfigCache();

    void loadingConfigCache();
}
