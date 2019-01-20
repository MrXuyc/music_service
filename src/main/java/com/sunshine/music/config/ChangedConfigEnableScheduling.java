package com.sunshine.music.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@EnableScheduling
@RestController
public class ChangedConfigEnableScheduling implements SchedulingConfigurer {

    private String cron="0/5 * * * * *";

    private final static Logger logger = LoggerFactory.getLogger(ChangedConfigEnableScheduling.class);
    @GetMapping("/changeCron")
    public String changeCron(){
        cron="0/10 * * * * *";
        return "changed";
    }


    /**
     * 回去监听cron是否被改变  改变的话会修改定时时间
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        Runnable task=new Runnable() {
            @Override
            public void run() {
                logger.info("动态改变定时任务");
            }
        };

        Trigger trigger=new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger cronTrigger=new CronTrigger(cron);
                return cronTrigger.nextExecutionTime(triggerContext);
            }
        };
        scheduledTaskRegistrar.addTriggerTask(task,trigger);
    }
}
