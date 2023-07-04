package com.zhiyu;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan({"com.zhiyu.**.mapper"})
public class ZhiYuAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhiYuAdminApplication.class, args);
    }

}
