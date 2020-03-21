package com.monkeyzi.code.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2020/1/3 23:52
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
@Data
@Configuration
@PropertySource(value = {"classpath:monx.properties"})
@ConfigurationProperties(prefix = "monx")
public class MonxProperties {
    /**
     * 匿名访问地址
     */
   private List<String> anonUrl=new ArrayList<>();
    /**
     * shiro相关配置
     */
   private ShiroProperties shiro=new ShiroProperties();
}
