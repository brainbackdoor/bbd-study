package com.brainbackdoor.quatz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class BaseJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        doExecute(jobExecutionContext);
    }
    protected abstract void doExecute(JobExecutionContext context);
}
