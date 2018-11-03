package com.brainbackdoor.quatz;

import org.quartz.JobExecutionContext;

public class HelloJob4nd extends BaseJobTemplate {
    @Override
    protected void doExecute(JobExecutionContext context) {
        System.out.println("Hello Job 4nd" + context.getJobDetail().getJobDataMap().get("JobName").toString());
    }
}
