package com.zhiyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.zhiyu.common.core.entity.BaseEntity;
import lombok.Data;

@Data
public class SysDictType extends BaseEntity {
    /** 字典主键 */
    @TableId
    private Long dictId;

    /** 字典名称 */
    private String dictName;

    /** 字典类型 */
    private String dictType;

}
