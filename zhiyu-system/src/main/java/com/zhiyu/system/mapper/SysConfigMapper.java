package com.zhiyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.core.entity.SysDept;
import com.zhiyu.system.entity.SysConfig;
import com.zhiyu.system.entity.query.SysConfigQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysConfigMapper extends BaseMapper<SysConfig> {
    IPage<SysConfig> pageQuery(IPage<SysDept> page, @Param("query") SysConfigQuery sysConfigQuery);

    /**
     * 查询参数配置信息
     *
     * @param config 参数配置信息
     * @return 参数配置信息
     */
    SysConfig selectConfig(SysConfig config);

    SysConfig checkConfigKeyUnique(String configKey);

    List<SysConfig> selectConfigList(SysConfig sysConfig);
}
