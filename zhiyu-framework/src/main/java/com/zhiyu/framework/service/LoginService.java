package com.zhiyu.framework.service;

import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.system.entity.LoginInfo;
import com.zhiyu.system.entity.LoginUserInfo;

public interface LoginService {
    ApiResult<String> login(LoginInfo user);

    ApiResult<LoginUserInfo> getUserInfo(Long userId);
}
