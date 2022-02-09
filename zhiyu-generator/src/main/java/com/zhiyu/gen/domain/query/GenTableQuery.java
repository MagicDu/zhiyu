package com.zhiyu.gen.domain.query;

import com.zhiyu.common.core.entity.BasePageInfo;
import com.zhiyu.gen.domain.GenTable;
import lombok.Data;


/**
 * 业务表 gen_table
 *
 * @author magicdu
 */
@Data
public class GenTableQuery extends BasePageInfo<GenTable> {
    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

}