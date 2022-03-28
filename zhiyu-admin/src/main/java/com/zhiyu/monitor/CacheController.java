package com.zhiyu.monitor;

import cn.hutool.core.util.StrUtil;
import com.zhiyu.common.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 缓存监控
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/cache")
public class CacheController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping()
    public ApiResult<Map<String, Object>> getInfo() throws Exception {
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());

        Map<String, Object> result = new HashMap<>(3);
        result.put("info", info);
        result.put("dbSize", dbSize);

        List<Map<String, String>> pieList = new ArrayList<>();
        commandStats.stringPropertyNames().forEach(key -> {
            Map<String, String> data = new HashMap<>(2);
            String property = commandStats.getProperty(key);
            data.put("name", StrUtil.removePrefix(key, "cmdstat_"));
            data.put("value", StrUtil.subBetween(property, "calls=", ",usec"));
            pieList.add(data);
        });
        result.put("commandStats", pieList);
        return new ApiResult<>(result);
    }
}
