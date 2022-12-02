package com.zhiyu.system.entity.query;

import com.zhiyu.common.core.entity.BasePageInfo;
import com.zhiyu.system.entity.SysConfig;
import lombok.Data;

@Data
public class SysConfigQuery extends BasePageInfo<SysConfig> {
    private String configName;
    private String configKey;
    private String configType;
}
