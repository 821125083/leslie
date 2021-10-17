package com.leslie.service;

import com.leslie.job.SchedulerJob;
import org.quartz.Job;

import java.util.List;
import java.util.Map;

public interface QuartzService {

    void testQuartz();

    List<Map<String, Object>> queryAllQuartzJob();

    String addQuartzCountDownJob(String jobName, Class<? extends Job> job, Object task, int seconds, String jobGorupName);

    void runAJobNow(String jobName, String jobGroupName);

    List<Map<String, Object>> queryRuningJob() ;

    void addQuartzCronJob(String jobName, Class<? extends Job> job, String task, String cron, String groupName);
}
