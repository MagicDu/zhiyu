package com.zhiyu.monitor;

import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.framework.web.domain.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor/server")
public class ServerController  {

    @GetMapping()
    public ApiResult<Server> getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return new ApiResult<>(server);
    }
}
