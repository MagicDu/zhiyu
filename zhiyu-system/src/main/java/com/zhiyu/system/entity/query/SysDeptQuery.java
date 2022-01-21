package com.zhiyu.system.entity.query;

import com.zhiyu.common.core.entity.BasePageInfo;
import com.zhiyu.common.core.entity.SysDept;
import lombok.Data;

@Data
public class SysDeptQuery extends BasePageInfo<SysDept> {
    private String deptName;
    private String deptCode;
}
