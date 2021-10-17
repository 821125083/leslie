package com.leslie.service.impl;

import com.leslie.job.AddMessageJob;
import com.leslie.job.SchedulerJob;
import com.leslie.service.QuartzService;
import org.quartz.*;
import org.quartz.core.jmx.JobDetailSupport;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void testQuartz() {
        System.out.println("testQuartz");
    }

    @Override
    public List<Map<String, Object>> queryAllQuartzJob() {
        List<Map<String, Object>> jobList = null;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            jobList = new ArrayList<Map<String, Object>>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("jobName", jobKey.getName());
                    map.put("jobGroupName", jobKey.getGroup());
                    map.put("description", "触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    map.put("jobStatus", triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        map.put("jobTime", cronExpression);
                    }
                    jobList.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public void runAJobNow(String jobName,String JobGroup){
        try {
            JobKey jobKey = JobKey.jobKey(jobName, JobGroup);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 倒计时任务
     * @param jobName
     * @param job
     * @param task
     * @param seconds
     * @param jobGroupName
     * @return
     */
    public String addQuartzCountDownJob(String jobName, Class<? extends Job> job, Object task, int seconds, String jobGroupName){
        try {
            // 判断任务是否存在
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            if (scheduler.checkExists(jobKey)) {
                return "任务已存在";// 任务已经存在
            }
            // 创建一个JobDetail实例，指定SimpleJob
            Map<String, Object> JobDetailmap = new HashMap<String, Object>();
            JobDetailmap.put("name", jobName);// 设置任务名字
            JobDetailmap.put("group", jobGroupName);// 设置任务组
            JobDetailmap.put("jobClass", job.getCanonicalName());// 指定执行类
            // Task.class.getCanonicalName()
            JobDetail jobDetail = JobDetailSupport.newJobDetail(JobDetailmap);
            // 添加数据内容
            jobDetail.getJobDataMap().put("task", task);// 传输的上下文
            // 通过SimpleTrigger定义调度规则：马上启动，每2秒运行一次，共运行100次 等。。。。
            SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl();
            simpleTrigger.setName(jobName);
            simpleTrigger.setGroup("testGroupName");
            // 什么时候开始执行
            simpleTrigger.setStartTime(new Date(new Date().getTime() + 1000 * seconds));
            // 间隔时间
            simpleTrigger.setRepeatInterval(1000 * seconds);
            // 最多执行次数 默认执行一次
            simpleTrigger.setRepeatCount(0);
            // 通过SchedulerFactory获取一个调度器实例
            scheduler.scheduleJob(jobDetail, simpleTrigger);//  注册并进行调度
            scheduler.start();// ⑤调度启动
            return "添加任务成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加任务失败";
        }
    }

    @Override
    public List<Map<String, Object>> queryRuningJob() {
        List<Map<String, Object>> jobList = null;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<Map<String, Object>>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                Map<String, Object> map = new HashMap<String, Object>();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                map.put("jobName", jobKey.getName());
                map.put("jobGroupName", jobKey.getGroup());
                map.put("description", "触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put("jobStatus", triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put("jobTime", cronExpression);
                }
                jobList.add(map);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }


    @Override
    public void addQuartzCronJob(String jobName, Class<? extends Job> job, String task, String cron, String groupName) {
        JobDetail jobDetail = JobBuilder
                .newJob(AddMessageJob.class)
                .withIdentity(jobName,groupName)
                .storeDurably().build();
        CronTrigger trigger = TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .startNow().build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            System.out.println("创建任务成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

