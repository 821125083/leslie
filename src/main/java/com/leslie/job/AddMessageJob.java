package com.leslie.job;

import com.leslie.pojo.Message;
import com.leslie.service.MessageService;
import com.leslie.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class AddMessageJob extends QuartzJobBean {

    @Autowired
    private MessageService messageService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Message message = new Message();
        message.setMessage(DateUtils.nowDateString());
        System.out.println("add message job execute success!");
        messageService.insertMessage(message);

    }
}
