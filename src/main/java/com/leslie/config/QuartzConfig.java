package com.leslie.config;

import com.leslie.job.SchedulerJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

//    @Bean
//    public JobDetail scheduleJobDetail() {
//        JobDetail jobDetail = JobBuilder.newJob(SchedulerJob.class)
//                .withIdentity("schedulerJob")
//                .storeDurably()
//                .build();
//        return jobDetail;
//    }
//
//    @Bean
//    public Trigger scheduleJobDetailTrigger() {
//        Trigger trigger = TriggerBuilder
//                .newTrigger()
//                .forJob(scheduleJobDetail())
//                .withIdentity("schedulerJob")
//                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
//                .startNow()
//                .build();
//        return trigger;
//    }


}
