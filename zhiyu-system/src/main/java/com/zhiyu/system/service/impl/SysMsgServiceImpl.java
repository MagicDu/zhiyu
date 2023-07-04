package com.zhiyu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.common.core.entity.SysMsg;
import com.zhiyu.system.mapper.SysMsgMapper;
import com.zhiyu.system.service.SysMsgService;
import org.springframework.stereotype.Service;

@Service
public class SysMsgServiceImpl extends ServiceImpl<SysMsgMapper, SysMsg> implements SysMsgService {
}
