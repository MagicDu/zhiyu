package com.zhiyu.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目基础配置
 */
@Component
@ConfigurationProperties(prefix = "magic")
public class ZhiYuConfig {
    // 文件上传路径
    private static String  uploadPath;

    public static String getUploadPath() {
        return uploadPath;
    }

    public  void setUploadPath(String uploadPath) {
        ZhiYuConfig.uploadPath = uploadPath;
    }
}
