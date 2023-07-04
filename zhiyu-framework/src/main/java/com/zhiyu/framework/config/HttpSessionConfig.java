package com.zhiyu.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Autowired
    private RedisTemplate redisTemplate;
//    @Bean("sessionRepository")
//    @ConditionalOnMissingBean(name = "sessionRepository")
//    public FindByIndexNameSessionRepository sessionRepository() {
//        return new RedisIndexedSessionRepository(redisTemplate);
//    }
}
