package com.zhiyu.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.common.constant.CacheConstants;
import com.zhiyu.common.constant.UserConstants;
import com.zhiyu.common.core.entity.SysDept;
import com.zhiyu.common.core.redis.RedisCache;
import com.zhiyu.system.entity.SysConfig;
import com.zhiyu.system.entity.query.SysConfigQuery;
import com.zhiyu.system.mapper.SysConfigMapper;
import com.zhiyu.system.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
    @Autowired
    private SysConfigMapper sysConfigMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public IPage<SysConfig> pageQuery(SysConfigQuery sysConfigQuery) {
        IPage<SysDept> page=new Page<>(sysConfigQuery.getCurrent(),sysConfigQuery.getSize());
        return sysConfigMapper.pageQuery(page,sysConfigQuery);
    }

    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
        if (StrUtil.isNotEmpty(configValue)) {
            return configValue;
        }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = sysConfigMapper.selectConfig(config);
        if (ObjectUtil.isNotNull(retConfig)) {
            redisCache.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StrUtil.EMPTY;
    }

    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        Long configId = ObjectUtil.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = sysConfigMapper.checkConfigKeyUnique(config.getConfigKey());
        if (ObjectUtil.isNotNull(info) && info.getConfigId().longValue() != configId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }

    @Override
    public void clearConfigCache() {
        Collection<String> keys = redisCache.keys(CacheConstants.SYS_CONFIG_KEY + "*");
        redisCache.deleteObject(keys);
    }

    @Override
    public void loadingConfigCache() {
        List<SysConfig> configsList = sysConfigMapper.selectConfigList(new SysConfig());
        for (SysConfig config : configsList) {
            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return CacheConstants.SYS_CONFIG_KEY + configKey;
    }
}
