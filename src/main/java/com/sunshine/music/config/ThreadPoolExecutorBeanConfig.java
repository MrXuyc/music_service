package com.sunshine.music.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolExecutorBeanConfig {

    @Value("${threadPool.corePoolSize}")
    private int corePoolSize;

    @Value("${threadPool.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${threadPool.keepAliveTime}")
    private long keepAliveTime;

    @Value("${threadPool.capacity}")
    private int capacity;


    @Bean(name = "apiTaskThreadPollExecutor")
    public ThreadPoolExecutor apiTaskThreadPollExecutor() {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingDeque(capacity));
    }

    @Bean(name = "mailTaskThreadPoolExecutor")
    public ThreadPoolExecutor mailTaskThreadPoolExecutor() {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingDeque(capacity));
    }

    @Bean(name = "preApiTaskThreadPollExecutor")
    public ThreadPoolExecutor preApiTaskThreadPollExecutor() {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingDeque(capacity));
    }
}
