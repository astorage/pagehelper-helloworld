package com.java.pagehelperhelloworld.configuration;


import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Slf4j
@Configuration
public class PageHelperConfiguration {

    @Bean
    public PageInterceptor pageHelper() {
        log.info("注册MyBatis分页插件PageInterceptor------begin");

        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageInterceptor.setProperties(p);

        log.info("注册MyBatis分页插件PageInterceptor------successfully");

        return pageInterceptor;
    }
}
