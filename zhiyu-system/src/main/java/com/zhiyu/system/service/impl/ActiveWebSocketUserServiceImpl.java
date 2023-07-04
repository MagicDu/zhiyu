package com.zhiyu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.common.core.entity.ActiveWebSocketUser;
import com.zhiyu.system.mapper.ActiveWebSocketUserMapper;
import com.zhiyu.system.service.ActiveWebSocketUserService;
import org.springframework.stereotype.Service;

@Service
public class ActiveWebSocketUserServiceImpl extends ServiceImpl<ActiveWebSocketUserMapper, ActiveWebSocketUser> implements ActiveWebSocketUserService {
}
