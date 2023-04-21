package com.zhiyu.system.entity.query;

import com.zhiyu.common.core.entity.BasePageInfo;
import com.zhiyu.common.core.entity.SysPost;
import lombok.Data;

@Data
public class SysPostQuery extends BasePageInfo<SysPost>{
    private String postName;
    private String postCode;
    private String status;
}
