package com.sunshine.basepro.config;

import com.github.pagehelper.PageHelper;
import com.sunshine.basepro.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class MyBatisConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

    @Bean
    public PageHelper pageHelper() throws IOException {
        logger.info("");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.load(new FileInputStream(new File(MyBatisConfiguration.class.getResource("/").getPath()+"mybatis.properties")));
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
