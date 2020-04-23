package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {

    /**
     * 微信公众平台授权URL
     *
     */
    public String weChatMpAuthorize;

    /**
     * 微信开放平台url
     *
     */
    public String weChatOpenAuthorize;

    /**
     * 项目url
     */
    public String sell;



}
