package com.zhiyu.system.entity.query;

import com.zhiyu.common.core.entity.BasePageInfo;
import com.zhiyu.system.entity.SysDictData;
import lombok.Data;

@Data
public class SysDictDataQuery extends BasePageInfo<SysDictData> {
    private String dictType;
    private String dictLabel;
}
