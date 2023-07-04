package com.zhiyu.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysMsg  extends BaseEntity{
    @TableId
    private Long id;
    private String content;
    private String type;
    private Long receiveUser;
    private Long groupId;
}
