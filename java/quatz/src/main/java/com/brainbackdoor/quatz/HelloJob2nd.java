package com.brainbackdoor.quatz;

import org.quartz.JobExecutionContext;

public class HelloJob2nd extends BaseJob {
    @Override
    protected void doExecute(JobExecutionContext context) {
        System.out.println("Hello Job 2nd");
    }
}
