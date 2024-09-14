package com.tianpei.nytdforum.config;

import com.tianpei.nytdforum.utils.QiniuUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QiniuConfig {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.domain}")
    private String domain;

    @Value("${qiniu.useHttps}")
    private Boolean useHttps;

    @PostConstruct
    public void init() {
        QiniuUtil.initialize(accessKey, secretKey, bucket, domain, useHttps);
    }
}
