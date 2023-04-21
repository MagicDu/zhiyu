package com.zhiyu.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sys_post")
@Data
public class SysPost extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 岗位ID */
    @TableId
    private Long postId;

    /** 岗位编码 */
    private String postCode;

    /** 岗位名称 */
    private String postName;

    /** 显示顺序 */
    private String postSort;
}
