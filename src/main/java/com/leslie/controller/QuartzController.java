package com.leslie.controller;

import com.leslie.job.AddMessageJob;
import com.leslie.job.SchedulerJob;
import com.leslie.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("quartz")
@ResponseBody
public class QuartzController {

    @Autowired
    QuartzService quartzService;

    @RequestMapping("queryAllJob")
    public List<Map<String, Object>> queryAllJob() {

        return quartzService.queryAllQuartzJob();
    }

    @RequestMapping("addQuartzJob")
    public String addJob(){

        return quartzService.addQuartzCountDownJob("MY_TEST_JOB", SchedulerJob.class, "test", 200, "001");

    }

    @RequestMapping("addQuartzCronJob")
    public String addQuartzCronJob(){
        quartzService.addQuartzCronJob("addMessageJob", AddMessageJob.class, "task", "0 0/3 * * * ? *", "groupName");

        return null;
    }

    @RequestMapping("runAJobNow")
    public void runAJobNow(){

        quartzService.runAJobNow("addMessageJob", "groupName");

    }

    @RequestMapping("queryAllRunningJob")
    public List<Map<String, Object>> queryAllRunningJob(){

        return quartzService.queryRuningJob();

    }


}
