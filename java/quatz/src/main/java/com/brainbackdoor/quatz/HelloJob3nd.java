package com.brainbackdoor.quatz;

import org.quartz.JobExecutionContext;

public class HelloJob3nd extends BaseJobTemplate {
    @Override
    protected void doExecute(JobExecutionContext context) {
        System.out.println("Hello Job 3nd");
    }
}
