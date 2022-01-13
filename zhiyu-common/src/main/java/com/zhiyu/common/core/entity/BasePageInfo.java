package com.zhiyu.common.core.entity;

import lombok.Data;

@Data
public class BasePageInfo<T> {
    private Integer current=10;
    private Integer size=10;
    private T entity;
}
