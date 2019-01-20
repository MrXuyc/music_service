package com.sunshine.music.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ConfigEnableScheduling {
    private final static Logger logger = LoggerFactory.getLogger(ConfigEnableScheduling.class);

    /**
     *   ,作为分割   *  任意   /  每    -这个区间执行（相当于这个区间进行带入表达式，进行执行）
     *    *表示任意
     *    ？不想设置（不关心）   日期  和星期使用
     *    秒 分 小时 日期 月份 星期  年（可选）
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void task(){
        logger.info("定时任务开启");
    }

}
