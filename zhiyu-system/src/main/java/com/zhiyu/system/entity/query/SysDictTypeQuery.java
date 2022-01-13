package com.zhiyu.system.entity.query;

import com.zhiyu.common.core.entity.BasePageInfo;
import com.zhiyu.system.entity.SysDictType;
import lombok.Data;

@Data
public class SysDictTypeQuery extends BasePageInfo<SysDictType> {

    /** 字典名称 */
    private String dictName;

    /** 字典类型 */
    private String dictType;

}
